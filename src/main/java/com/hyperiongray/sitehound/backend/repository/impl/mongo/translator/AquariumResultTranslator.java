package com.hyperiongray.sitehound.backend.repository.impl.mongo.translator;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import com.hyperiongray.sitehound.backend.service.nlp.NlpOutput;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by tomas on 2/10/16.
 */
@Component
public class AquariumResultTranslator{

	public Map<String, Object> translateToDocument(AquariumInput aquariumInput, AquariumInternal aquariumInternal, NlpOutput nlpOutput){

		Map<String, Object> document = Maps.newHashMap();
		document.put("source", aquariumInput.getMetadata().getSource());
		document.put("jobId", aquariumInput.getMetadata().getJobId());
		document.put("timestamp", aquariumInput.getMetadata().getTimestamp());
		document.put("strTimestamp", aquariumInput.getMetadata().getStrTimestamp());
		document.put("nResults", aquariumInput.getMetadata().getnResults());
		document.put("provider", aquariumInput.getMetadata().getProvider().toString());
		document.put("crawlEntityType", aquariumInput.getMetadata().getCrawlEntityType().toString());

		if(aquariumInput.getMetadata().getCrawlEntityType().equals(Constants.CrawlEntityType.MANUAL)){
			document.put("relevant", true);
		}

		document.put("host", CrawlerUtils.getHostName(aquariumInput.getUrl()));
		document.put("url", aquariumInput.getUrl().toLowerCase());

		//TODO thh --> don't save this yet, because it's being returned in thh view
//		document.put("html", aquariumInternal.getHtml());

		//TODO thh --> for compatibility reasons
		//document.put("png", aquariumInternal.getPng());
		Map<String, Object> snapshot = Maps.newHashMap();
		snapshot.put("png", aquariumInternal.getJpeg() != null ? aquariumInternal.getJpeg() : aquariumInternal.getPng() );
		document.put("snapshot", snapshot);

		document.put("desc", nlpOutput.getDesc());
		document.put("urlDesc", nlpOutput.getUrlDesc());
		document.put("score", nlpOutput.getScore());
		document.put("words", nlpOutput.getWords());
//		TODO document.put("wordsfrequency", nlpOutput.getWordsFrequency());
		document.put("categories", nlpOutput.getCategories());
		document.put("language", nlpOutput.getLanguage());
		return document;
	}


	//	@NotNull
//	private CrawledUrl getCrawledUrl(AquariumInternal aquariumInternal, NlpOutput nlpOutput){
//		CrawledUrl crawledUrl = new CrawledUrl(aquariumInput.getUrl().toLowerCase(), aquariumInput.getMetadata(), Constants.CrawlerResult.SUCCESS);
//		crawledUrl.setHost(CrawlerUtils.getDomainName(aquariumInput.getUrl()));
//		crawledUrl.setDesc(nlpOutput.getDesc());
//		crawledUrl.setUrlDesc(nlpOutput.getUrlDesc());
//		crawledUrl.setWords(nlpOutput.getWords());
//		crawledUrl.setLinks(nlpOutput.getLinks());
//		crawledUrl.setCategories(nlpOutput.getCategories());
//		crawledUrl.setLanguage(nlpOutput.getLanguage());
//		crawledUrl.setnRetries(0);
//		CrawledUrl.Snapshot snapshot = new CrawledUrl.Snapshot();
//		snapshot.setPng(aquariumInternal.getJpeg() != null ? aquariumInternal.getJpeg() : aquariumInternal.getPng());
//		snapshot.setHtml(aquariumInternal.getHtml());
//		crawledUrl.setSnapshot(snapshot);
//		return crawledUrl;
//	}



}
