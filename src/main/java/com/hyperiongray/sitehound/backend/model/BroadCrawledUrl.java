package com.hyperiongray.sitehound.backend.model;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 11/18/15.
 */
public class BroadCrawledUrl extends CrawledUrl{

	private Boolean deleted;
	private Boolean pinned;
	private Double score;

	public BroadCrawledUrl(String url, Metadata metadata, Constants.CrawlerResult crawlerResult){
		super(url, metadata, crawlerResult);
	}


	public Boolean getDeleted(){
		return deleted;
	}

	public void setDeleted(Boolean deleted){
		this.deleted=deleted;
	}

	public Boolean getPinned(){
		return pinned;
	}

	public void setPinned(Boolean pinned){
		this.pinned=pinned;
	}

	public Double getScore(){
		return score;
	}

	public void setScore(Double score){
		this.score=score;
	}


}
