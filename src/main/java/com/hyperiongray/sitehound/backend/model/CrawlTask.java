package com.hyperiongray.sitehound.backend.model;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 11/16/15.
 */
public class CrawlTask{

	private String taskId;
	private String jobId;
	private Integer nResultsFound;
	private Constants.CrawlEntityType crawlEntityType;

	public CrawlTask(String jobId, Constants.CrawlEntityType crawlEntityType, Integer nResultsFound){
		this.jobId=jobId;
		this.nResultsFound=nResultsFound;
		this.taskId=jobId +"-"+ crawlEntityType;
		this.crawlEntityType=crawlEntityType;
	}

	public String getTaskId(){
		return taskId;
	}

	public Constants.CrawlEntityType getCrawlEntityType(){
		return crawlEntityType;
	}

	public Integer getnResultsFound(){
		return nResultsFound;
	}

	public void setnResultsFound(Integer nResultsFound){
		this.nResultsFound=nResultsFound;
	}

	public String getJobId(){
		return jobId;
	}
}
