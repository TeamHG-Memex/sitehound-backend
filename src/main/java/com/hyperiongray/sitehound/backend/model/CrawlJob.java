package com.hyperiongray.sitehound.backend.model;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;

import java.util.Calendar;

/**
 * Created by tomas on 11/16/15.
 */
public class CrawlJob{

	private String jobId;
	private String workspace;
	private String source;
	private Long timestamp;
	private Constants.CrawlerProvider provider;
	private Constants.CrawlType crawlType;
	private Integer nResultsRequested;
//	private List<CrawlTask> crawlTasks = new LinkedList<>();

	public CrawlJob(String workspace, String jobId, String source, Constants.CrawlType crawlType, Integer nResultsRequested, Constants.CrawlerProvider provider){
		this.workspace=workspace;
		this.jobId=jobId;
		this.source=source;
		this.crawlType=crawlType;
		this.nResultsRequested=nResultsRequested;
		this.provider=provider;
		this.timestamp=Calendar.getInstance().getTimeInMillis();
	}

	public String getJobId(){
		return jobId;
	}

	public String getWorkspace(){
		return workspace;
	}

	public String getSource(){
		return source;
	}

	public Long getTimestamp(){
		return timestamp;
	}

	public Constants.CrawlerProvider getProvider(){
		return provider;
	}

	public Constants.CrawlType getCrawlType(){
		return crawlType;
	}

	public Integer getnResultsRequested(){
		return nResultsRequested;
	}



}
