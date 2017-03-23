package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 2/11/16.
 */
public class BroadCrawlContextDto extends CrawlContextDto{

	private Double score;

	public Constants.CrawlType getCrawlType(){
		return Constants.CrawlType.BROADCRAWL;
	}

	public BroadCrawlContextDto(CrawlRequestDto crawlRequestDto, AnalyzedCrawlResultDto analyzedCrawlResultDto, Double score){
		super(crawlRequestDto, analyzedCrawlResultDto);
		this.score = score;
	}

	public Double getScore(){
		return score;
	}
}
