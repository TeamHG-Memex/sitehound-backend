package com.hyperiongray.sitehound.backend.model;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

import java.util.*;

/**
 * Created by tomas on 11/16/15.
 */
public class CrawledUrl{

	private long timestamp;
	private String host;
	private String url;
	private Snapshot snapshot;
	private String desc;
	private String urlDesc;

	private List<String> links;
	private List<String> words = new LinkedList<>();
	private Set<String> categories = new HashSet<>();
	private String language;

	private Constants.CrawlerResult crawlerResult;
	private Integer nRetries;
	private String lastError;
	private Metadata metadata;

	public CrawledUrl(String url, Metadata metadata, Constants.CrawlerResult crawlerResult){
		this.url=url;
		this.metadata=metadata;
		this.crawlerResult=crawlerResult;
		this.timestamp = Calendar.getInstance().getTimeInMillis();
	}

	public long getTimestamp(){
		return timestamp;
	}

	public String getHost(){
		return host;
	}

	public void setHost(String host){
		this.host=host;
	}

	public String getUrl(){
		return url;
	}

	public Snapshot getSnapshot(){
		return snapshot;
	}

	public void setSnapshot(Snapshot snapshot){
		this.snapshot=snapshot;
	}

	public String getDesc(){
		return desc;
	}

	public void setDesc(String desc){
		this.desc=desc;
	}

	public String getUrlDesc(){
		return urlDesc;
	}

	public void setUrlDesc(String urlDesc){
		this.urlDesc=urlDesc;
	}

	public List<String> getWords(){
		return words;
	}

	public void setWords(List<String> words){
		this.words=words;
	}

	public Set<String> getCategories(){
		return categories;
	}

	public void setCategories(Set<String> categories){
		this.categories=categories;
	}

	public String getLanguage(){
		return language;
	}

	public void setLanguage(String language){
		this.language=language;
	}

	public Constants.CrawlerResult getCrawlerResult(){
		return crawlerResult;
	}

	public Integer getnRetries(){
		return nRetries;
	}

	public void setnRetries(Integer nRetries){
		this.nRetries=nRetries;
	}

	public String getLastError(){
		return lastError;
	}

	public void setLastError(String lastError){
		this.lastError=lastError;
	}

	public List<String> getLinks(){
		return links;
	}

	public void setLinks(List<String> links){
		this.links=links;
	}

	public static class Snapshot {
		private String png;
		private String html;

		public String getHtml(){
			return html;

		}

		public void setHtml(String html){
			this.html=html;
		}

		public String getPng(){
			return png;
		}

		public void setPng(String png){
			this.png=png;
		}
	}
}


