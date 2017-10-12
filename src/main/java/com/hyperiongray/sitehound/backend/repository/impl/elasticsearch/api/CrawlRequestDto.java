package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 2/11/16.
 */
public class CrawlRequestDto{

	private String url;
	private String workspace;
	private String jobId;
	private String uow;
	private Long timestamp;
	private Constants.CrawlEntityType crawlEntityType;
	private Constants.CrawlType crawlType;
	private Constants.KeywordSourceType keywordSourceType;

	public CrawlRequestDto(String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public Long getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}

	public String getWorkspace(){
		return workspace;
	}

	public void setWorkspace(String workspace){
		this.workspace = workspace;
	}

	public Constants.CrawlEntityType getCrawlEntityType(){
		return crawlEntityType;
	}

	public void setCrawlEntityType(Constants.CrawlEntityType crawlEntityType){
		this.crawlEntityType = crawlEntityType;
	}

	public Constants.CrawlType getCrawlType(){
		return crawlType;
	}

	public void setCrawlType(Constants.CrawlType crawlType){
		this.crawlType = crawlType;
	}

	public String getUow(){
		return uow;
	}

	public void setUow(String uow){
		this.uow = uow;
	}

	public String getJobId(){
		return jobId;
	}

	public void setJobId(String jobId){
		this.jobId = jobId;
	}

	public Constants.KeywordSourceType getKeywordSourceType() {
		return keywordSourceType;
	}

	public void setKeywordSourceType(Constants.KeywordSourceType keywordSourceType) {
		this.keywordSourceType = keywordSourceType;
	}
}
