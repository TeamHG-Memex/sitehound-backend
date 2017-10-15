package com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.translator;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlContextDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlRequestDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tomas on 2/12/16.
 */
@Service
public class DefaultCrawlContextDtoTranslator{

//	public Map<String, Object> translate(String hashKey, CrawlRequestDto crawlRequestDto, AnalyzedCrawlResultDto analyzedCrawlResultDto, Double score){
//		Map<String, Object> document = translate(hashKey, crawlRequestDto, analyzedCrawlResultDto);
//		document.put("score", score);
//		return document;
//	}

	public Map<String, Object> translate(String hashKey, CrawlRequestDto crawlRequestDto, AnalyzedCrawlResultDto analyzedCrawlResultDto){
		Map<String, Object> document = Maps.newHashMap();
		document.put("hashKey", hashKey);
		document.put("workspaceId", crawlRequestDto.getWorkspace());
		document.put("jobId", crawlRequestDto.getJobId());
		document.put("crawlEntityType", crawlRequestDto.getCrawlEntityType().toString());

		if(crawlRequestDto.getKeywordSourceType()!=null){
			document.put("keywordSourceType", crawlRequestDto.getKeywordSourceType().toString());
		}
		else{
			System.out.println("crawlRequestDto not keywordSourceType:" + crawlRequestDto);
		}

		if(crawlRequestDto.getKeywordSearchWeb()!=null){
			document.put("keywordSearchWeb", crawlRequestDto.getKeywordSearchWeb().toString());
		} else{
			System.out.println("crawlRequestDto not keywordSearchWeb:" + crawlRequestDto);
		}

		document.put("timestamp", System.currentTimeMillis());
//		document.put("provider", Constants.CrawlerProvider.HH_JOOGLE.toString());
		document.put("url", crawlRequestDto.getUrl());
		document.put("host", analyzedCrawlResultDto.getCrawlResultDto().getHost().toLowerCase());
//		document.put("language", analyzedCrawlResultDto.getLanguage()); // this is also stored in Elasticsearch
//		document.put("categories", analyzedCrawlResultDto.getCategories());  // this is also stored in Elasticsearch
//		document.put("words", analyzedCrawlResultDto.getWords());  // this is also stored in Elasticsearch
		document.put("title", analyzedCrawlResultDto.getCrawlResultDto().getTitle());  // this is also stored in Elasticsearch
		document.put("aquariumed", true);  // this is also stored in Elasticsearch

		return document;
	}

}
