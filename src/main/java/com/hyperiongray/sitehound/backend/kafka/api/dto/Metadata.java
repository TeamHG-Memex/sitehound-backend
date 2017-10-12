package com.hyperiongray.sitehound.backend.kafka.api.dto;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 9/18/15.
 */
public class Metadata extends KafkaDto {
	private String workspace;
	private String jobId;
	private String strTimestamp;
	private Constants.CrawlEntityType crawlEntityType;
	private Constants.CrawlType crawlType;
	private Integer nResults;
	private String uow;
	private Constants.KeywordSourceType keywordSourceType;

	//	@Deprecated private String source;
//	@Deprecated private Constants.CrawlerProvider provider = Constants.CrawlerProvider.HH_JOOGLE;
//	@Deprecated private Long timestamp;
//	@Deprecated private String callbackQueue;


	public String getStrTimestamp(){
		return strTimestamp;
	}

	public void setStrTimestamp(String strTimestamp){
		this.strTimestamp=strTimestamp;
	}

	public String getWorkspace(){
		return workspace;
	}

	public void setWorkspace(String workspace){
		this.workspace=workspace;
	}

	public void setCrawlEntityType(Constants.CrawlEntityType crawlEntityType){
		this.crawlEntityType=crawlEntityType;
	}

	public Constants.CrawlEntityType getCrawlEntityType(){
		return crawlEntityType;
	}

	public Integer getnResults(){
		return nResults;
	}

	public void setnResults(Integer nResults){
		this.nResults=nResults;
	}

	public String getUow(){
		return uow;
	}

	public void setUow(String uow){
		this.uow=uow;
	}

	public String getJobId(){
		return jobId;
	}

	public void setJobId(String jobId){
		this.jobId=jobId;
	}

	public Constants.CrawlType getCrawlType(){
		return crawlType;
	}

	public void setCrawlType(Constants.CrawlType crawlType){
		this.crawlType=crawlType;
	}

	public Constants.KeywordSourceType getKeywordSourceType() {
		return keywordSourceType;
	}
	public void setKeywordSourceType(Constants.KeywordSourceType keywordSourceType) {
		this.keywordSourceType = keywordSourceType;
	}

	@Override
	public String toString(){
		return "Metadata{" +
				       ", strTimestamp='" + strTimestamp + '\'' +
				       ", workspace='" + workspace + '\'' +
				       ", crawlEntityType=" + crawlEntityType +
				       ", keywordSourceType=" + keywordSourceType +
				       ", crawlType=" + crawlType +
				       ", nResults=" + nResults +
				       ", uow='" + uow + '\'' +
				       ", jobId='" + jobId + '\'' +
				       '}';
	}

}
