package com.hyperiongray.sitehound.backend.model;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 11/18/15.
 */
public class KeywordCrawledUrl extends CrawledUrl{

	private Boolean relevant;
	private Constants.CrawlEntityType crawlEntityType;

	public KeywordCrawledUrl(String url, Metadata metadata, Constants.CrawlerResult crawlerResult){
		super(url, metadata, crawlerResult);
	}


	public Boolean getRelevant(){
		return relevant;
	}

	public void setRelevant(Boolean relevant){
		this.relevant=relevant;
	}

	public Constants.CrawlEntityType getCrawlEntityType(){
		return crawlEntityType;
	}

	public void setCrawlEntityType(Constants.CrawlEntityType crawlEntityType){
		this.crawlEntityType=crawlEntityType;
	}
}
