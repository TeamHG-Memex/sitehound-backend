package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 2/11/16.
 */
public class DeepCrawlContextDto extends CrawlContextDto{

	public Constants.CrawlType getCrawlType(){
		return Constants.CrawlType.DEEPCRAWL;
	}

	public DeepCrawlContextDto(CrawlRequestDto crawlRequestDto, AnalyzedCrawlResultDto analyzedCrawlResultDto){
		super(crawlRequestDto, analyzedCrawlResultDto);
	}

}
