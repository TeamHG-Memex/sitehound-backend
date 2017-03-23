package com.hyperiongray.sitehound.backend.kafka.api.dto.crawler;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 7/8/15.
 */
public class SubscriberOutput {

	//from crawling results
	private String url;
	private String host;
	private Constants.CrawlEntityType crawlEntityType;
	private String desc;
	private String urlDesc;
	private String uow;


	// service additions
	private List<String> words = new LinkedList();
	private String snapshot;
	private String workspace;
	private int score;
	private Constants.CrawlerProvider provider;
	private String jobId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Constants.CrawlEntityType getCrawlEntityType() {
		return crawlEntityType;
	}

	public void setCrawlEntityType(Constants.CrawlEntityType crawlEntityType) {
		this.crawlEntityType = crawlEntityType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrlDesc() {
		return urlDesc;
	}

	public void setUrlDesc(String urlDesc) {
		this.urlDesc = urlDesc;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public Constants.CrawlerProvider getProvider() {
		return provider;
	}

	public void setProvider(Constants.CrawlerProvider provider) {
		this.provider = provider;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId=jobId;
	}

	public String getUow() {
		return uow;
	}

	public void setUow(String uow) {
		this.uow = uow;
	}

	@Override
	public String toString() {
		return "SubscriberOutput{" +
				"url='" + url + '\'' +
				", host='" + host + '\'' +
				", crawlEntityType=" + crawlEntityType +
				", desc='" + desc + '\'' +
				", urlDesc='" + urlDesc + '\'' +
				", uow='" + uow + '\'' +
				", words=" + words +
				", snapshot='" + snapshot + '\'' +
				", workspace='" + workspace + '\'' +
				", score=" + score +
				", provider=" + provider +
				", jobId='" + jobId + '\'' +
				'}';
	}
}
