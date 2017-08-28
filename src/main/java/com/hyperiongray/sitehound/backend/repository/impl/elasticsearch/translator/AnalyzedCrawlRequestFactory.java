package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import com.hyperiongray.sitehound.backend.service.nlp.ParserService;
import com.hyperiongray.sitehound.backend.service.nlp.WebCategoryService;
import com.hyperiongray.sitehound.backend.service.nlp.WordCounter;
import com.hyperiongray.sitehound.backend.service.nlp.classifier.ClassifierService;
import com.hyperiongray.sitehound.backend.service.nlp.language.LanguageService;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.ScorerService;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaOutput;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * Created by tomas on 2/11/16.
 */
@Service
public class AnalyzedCrawlRequestFactory{
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzedCrawlRequestFactory.class);

	@Value( "${classifier.enabled}" ) private boolean classifierEnabled;

	@Autowired private WordCounter wordCounter;
	@Autowired private ScorerService scorerService;
	@Autowired private TikaService tikaService;
	@Autowired private WebCategoryService webCategoryService;
	@Autowired private ParserService parserService;
	@Autowired private LanguageService languageService;
	@Autowired private ClassifierService classifierService;


	public AnalyzedCrawlResultDto build(CrawlResultDto crawlResultDto) throws Exception {
		return new AnalyzedCrawlResultDto(crawlResultDto);
	}

	public AnalyzedCrawlResultDto buildFull(CrawlResultDto crawlResultDto) throws Exception{

		AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);

		Document cleanHtmlDocument = parserService.clean(Jsoup.parse(crawlResultDto.getHtml()));

		String cleanBody = Jsoup.clean(crawlResultDto.getHtml(), Whitelist.relaxed());
		TikaOutput tikaOutput=tikaService.parseHtml(cleanBody);

		analyzedCrawlResultDto.setLinks(tikaOutput.getLinks());

		if(classifierEnabled){
			try{
				classifierService.classify(new URI(crawlResultDto.getUrl()), crawlResultDto.getHtml());
			}
			catch (Exception e){
				LOGGER.error("ERROR: failed to classify: " + crawlResultDto.getUrl());
				LOGGER.info("falling back to previous classifier implementation");
				analyzedCrawlResultDto.addCategories(webCategoryService.categorizeByText(tikaOutput.getPageMetadata()));
				analyzedCrawlResultDto.addCategories(webCategoryService.categorizeByFooter(cleanHtmlDocument));
			}
		}
		else{
			analyzedCrawlResultDto.addCategories(webCategoryService.categorizeByText(tikaOutput.getPageMetadata()));
			analyzedCrawlResultDto.addCategories(webCategoryService.categorizeByFooter(cleanHtmlDocument));
		}

		String stringify = parserService.stringify(crawlResultDto.getHtml());
//		nlpOutput.setDesc(stringify);
		analyzedCrawlResultDto.setText(stringify);
		analyzedCrawlResultDto.setLanguage(languageService.identifyLanguage(stringify));
		analyzedCrawlResultDto.setWords(wordCounter.getMostFrequentWords(stringify));
		return analyzedCrawlResultDto;
	}
}
