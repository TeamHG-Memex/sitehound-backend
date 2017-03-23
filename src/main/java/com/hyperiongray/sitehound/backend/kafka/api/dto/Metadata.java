package com.hyperiongray.sitehound.backend.kafka.api.dto;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 9/18/15.
 */
public class Metadata extends KafkaDto {
	private Long timestamp;
	private String strTimestamp;
	private String workspace;
	private String source;
	private String callbackQueue;
	private Constants.CrawlerProvider provider = Constants.CrawlerProvider.HH_JOOGLE;
	private Constants.CrawlEntityType crawlEntityType;
	private Constants.CrawlType crawlType;
	private Integer nResults;
	private String uow;
	private String jobId;


	public Long getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(Long timestamp){
		this.timestamp=timestamp;
	}

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

	public String getSource(){
		return source;
	}

	public void setSource(String source){
		this.source=source;
	}

	public String getCallbackQueue(){
		return callbackQueue;
	}

	public void setCallbackQueue(String callbackQueue){
		this.callbackQueue=callbackQueue;
	}

	public void setProvider(Constants.CrawlerProvider provider){
		this.provider=provider;
	}

	public Constants.CrawlerProvider getProvider(){
		return provider;
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

	@Override
	public String toString(){
		return "Metadata{" +
				       "timestamp=" + timestamp +
				       ", strTimestamp='" + strTimestamp + '\'' +
				       ", workspace='" + workspace + '\'' +
				       ", source='" + source + '\'' +
				       ", callbackQueue='" + callbackQueue + '\'' +
				       ", provider=" + provider +
				       ", crawlEntityType=" + crawlEntityType +
				       ", crawlType=" + crawlType +
				       ", nResults=" + nResults +
				       ", uow='" + uow + '\'' +
				       ", jobId='" + jobId + '\'' +
				       '}';
	}
}
