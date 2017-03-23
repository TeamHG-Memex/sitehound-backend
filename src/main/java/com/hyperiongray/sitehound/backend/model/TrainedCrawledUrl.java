package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 11/18/15.
 */
public class TrainedCrawledUrl{

	private String url;
	private String content;
	private Boolean relevant;

	public TrainedCrawledUrl() {
	}

	public TrainedCrawledUrl(String url, String content, Boolean relevant) {
		this.url = url;
		this.content = content;
		this.relevant = relevant;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content=content;
	}

	public Boolean getRelevant(){
		return relevant;
	}

	public void setRelevant(Boolean relevant){
		this.relevant=relevant;
	}

	@Override
	public String toString(){
		return "TrainedCrawledUrl{" +
				       "url='" + url + '\'' +
				       ", content='" + content.length() + '\'' +
				       ", relevant=" + relevant +
				       '}';
	}
}
