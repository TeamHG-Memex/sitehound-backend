package com.hyperiongray.sitehound.backend.service.nlp;

import org.springframework.stereotype.Service;

/**
 * Created by tomas on 9/20/15.
 */
@Service
@Deprecated
public class NlpService{

	/*
	@Autowired private WordCounter wordCounter;
	@Autowired private ScorerService scorerService;
	@Autowired private TikaService tikaService;
	@Autowired private WebCategoryService webCategoryService;
	@Autowired private ParserService parserService;
	@Autowired private LanguageService languageService;


	private static final Logger LOGGER = LoggerFactory.getLogger(NlpService.class);

	@Deprecated
	public CrawlContextDto getDdTrainerInputStart(CrawlRequestDto crawlRequestDto, CrawlResultDto crawlResultDto){

		AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);



		Double score = 0.00001;
		CrawlContextDto crawlContextDto = new CrawlContextDto(crawlRequestDto, analyzedCrawlResultDto, score);
		return crawlContextDto;
	}




	@Deprecated
	public NlpOutput getDdTrainerInputStart(AquariumInput aquariumInput, AquariumInternal aquariumInternal){

		NlpOutput nlpOutput = new NlpOutput(aquariumInput.getMetadata());

		try{
			LOGGER.info("starting NLP Processing:" + aquariumInput.getUrl());

			Document htmlDocument = Jsoup.parse(aquariumInternal.getHtml());
			nlpOutput.setUrlDesc(htmlDocument.title());


			Document cleanHtmlDocument = parserService.clean(Jsoup.parse(aquariumInternal.getHtml()));
			nlpOutput.setHtmlAsText(cleanHtmlDocument.text());
			nlpOutput.addCategories(webCategoryService.categorizeByFooter(cleanHtmlDocument));


			String cleanBody = Jsoup.clean(aquariumInternal.getHtml(), Whitelist.relaxed());
			TikaOutput tikaOutput=tikaService.parseHtml(cleanBody);
			nlpOutput.setLinks(tikaOutput.getLinks());
			nlpOutput.addCategories(webCategoryService.categorizeByText(tikaOutput.getPageMetadata()));


			String stringify = parserService.stringify(aquariumInternal.getHtml());
			nlpOutput.setDesc(stringify);
			nlpOutput.setLanguage(languageService.identifyLanguage(stringify));
			nlpOutput.setWords(wordCounter.getMostFrequentWords(stringify));


			if(aquariumInput.getMetadata().getCrawlType()==Constants.CrawlType.BROADCRAWL){
				TrainedCrawledUrl trainedCrawledUrl = new TrainedCrawledUrl();
				trainedCrawledUrl.setUrl(aquariumInput.getUrl());
				trainedCrawledUrl.setContent(nlpOutput.getDesc());
				Double score = scorerService.score(aquariumInput.getMetadata().getWorkspace(), aquariumInput.getMetadata().getJobId(), trainedCrawledUrl);
				nlpOutput.setScore(score * 100);
			}
			LOGGER.info("Finish NLP Processing:" + aquariumInput.getUrl());
		}catch(Exception e){
			LOGGER.error("Error parsing: " + aquariumInput.getMetadata(), e);
		}
		return nlpOutput;
	}
*/
}
