package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 2/11/16.
 */
public abstract class CrawlContextDto{

	protected CrawlRequestDto crawlRequestDto;
	protected AnalyzedCrawlResultDto analyzedCrawlResultDto;

	public CrawlContextDto(CrawlRequestDto crawlRequestDto, AnalyzedCrawlResultDto analyzedCrawlResultDto){
		this.crawlRequestDto = crawlRequestDto;
		this.analyzedCrawlResultDto = analyzedCrawlResultDto;
	}

	public abstract Constants.CrawlType getCrawlType();

	public CrawlRequestDto getCrawlRequestDto(){
		return crawlRequestDto;
	}

	public AnalyzedCrawlResultDto getAnalyzedCrawlResultDto(){
		return analyzedCrawlResultDto;
	}

}
