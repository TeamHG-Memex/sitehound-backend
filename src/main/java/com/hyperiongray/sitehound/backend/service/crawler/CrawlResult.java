package com.hyperiongray.sitehound.backend.service.crawler;

import java.io.Serializable;

/**
 * Created by tomas on 5/20/15.
 */
public abstract class CrawlResult implements Serializable{


	protected String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	private Constants.CrawlEntityType crawlEntityType;

	public Constants.CrawlEntityType getCrawlEntityType(){
		return crawlEntityType;
	}

	public CrawlResult(Constants.CrawlEntityType crawlEntityType){
		this.crawlEntityType = crawlEntityType;
	}

}
