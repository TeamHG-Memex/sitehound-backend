package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 11/18/15.
 */
public class TrainedCrawledUrl{

	private String url;
	private String text;
	private String html;
	private Boolean relevant;

	public TrainedCrawledUrl(String url, String text, String html, Boolean relevant) {
		this.url = url;
		this.text = text;
		this.html = html;
		this.relevant = relevant;
	}

	public TrainedCrawledUrl(String url, Boolean relevant) {
		this.url = url;
		this.relevant = relevant;
	}
	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}


	public Boolean getRelevant(){
		return relevant;
	}

	public void setRelevant(Boolean relevant){
		this.relevant=relevant;
	}

	@Override
	public String toString() {
		return "TrainedCrawledUrl{" +
				"url='" + url + '\'' +
				", relevant=" + relevant +
				", text='" + (text!=null? String.valueOf(text.length()): "0") + '\'' +
				", html='" + (html!=null? String.valueOf(html.length()): "0") +  + '\'' +
				'}';
	}
}
