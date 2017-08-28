package com.hyperiongray.sitehound.backend.model;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.junit.Assert;

import java.util.List;

/**
 * Created by tomas on 11/16/15.
 */
public class CrawlJob{

	private String workspaceId;
	private String jobId;
	private List<String> sources;
	private Long timestamp;
	private Constants.CrawlerProvider provider;
	private Constants.CrawlType crawlType;
	private Constants.CrawlEntityType crawlEntityType;
	private Constants.CrawlStatus crawlStatus;
	private Integer nResultsRequested;

	private CrawlJob(String workspaceId, String jobId, Constants.CrawlType crawlType, Constants.CrawlEntityType crawlEntityType, Constants.CrawlStatus crawlStatus, List<String> sources, Integer nResultsRequested, Constants.CrawlerProvider provider, Long timestamp){
		this.workspaceId=workspaceId;
		this.jobId=jobId;
		this.sources=sources;
		this.crawlType=crawlType;
		this.crawlEntityType = crawlEntityType;
		this.nResultsRequested=nResultsRequested;
		this.crawlStatus =crawlStatus;
		this.provider=provider;
		this.timestamp=timestamp;
	}

	public String getJobId(){
		return jobId;
	}

	public String getWorkspaceId(){
		return workspaceId;
	}

	public List<String> getSources(){
		return sources;
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

	public Constants.CrawlEntityType getCrawlEntityType() {
		return crawlEntityType;
	}

	public Constants.CrawlStatus getCrawlStatus() {
		return crawlStatus;
	}



	public static class Builder{
		private String workspaceId;
		private String jobId;
		private List<String> sources;
		private Long timestamp;
//		private Constants.CrawlerProvider provider;
		private Constants.CrawlType crawlType;
		private Constants.CrawlEntityType crawlEntityType;
		private Constants.CrawlStatus crawlStatus;
		private Integer nResultsRequested;


		public Builder withWorkspaceId(String workspaceId){
			this.workspaceId = workspaceId;
			return this;
		}

		public Builder withJobId(String jobId){
			this.jobId=jobId;
			return this;
		}

		public Builder withSources(List<String> sources){
			this.sources = sources;
			return this;
		}

		public Builder withTimestamp(Long timestamp){
			this.timestamp=timestamp;
			return this;
		}

		public Builder withCrawlType(Constants.CrawlType crawlType){
			this.crawlType = crawlType;
			return this;
		}

		public Builder withCrawlEntityType(Constants.CrawlEntityType crawlEntityType){
			this.crawlEntityType = crawlEntityType;
			return this;
		}

		public Builder withCrawlStatus(Constants.CrawlStatus crawlStatus){
			this.crawlStatus= crawlStatus;
			return this;
		}

		public Builder withNResultsRequested(Integer nResultsRequested){
			this.nResultsRequested = nResultsRequested;
			return this;
		}

		public CrawlJob build(){
			Assert.assertNotNull(this.workspaceId);
			Assert.assertNotNull(this.jobId);
			Assert.assertNotNull(this.sources);
			Assert.assertNotNull(this.crawlType);
			Assert.assertNotNull(this.crawlEntityType);
			Assert.assertNotNull(this.crawlStatus);
			Assert.assertNotNull(this.nResultsRequested);
			Assert.assertNotNull(this.timestamp);
			return new CrawlJob(this.workspaceId, this.jobId, this.crawlType, this.crawlEntityType, this.crawlStatus, this.sources, this.nResultsRequested, Constants.CrawlerProvider.HH_JOOGLE, this.timestamp);
		}

	}


}
