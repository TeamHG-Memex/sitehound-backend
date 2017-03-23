package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;

/**
 * Created by tomas on 2/11/16.
 */
public class CrawlResultDto{


	private String title; //urlDesc
	private String url;
	private String html;
	private ImageDto image;
	private Long timestamp;

	public CrawlResultDto(String url){
		this.url = url.toLowerCase();
		this.timestamp = System.currentTimeMillis();
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public Long getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}

	public String getHost(){
			return CrawlerUtils.getHostName(url);
	}

	public String getUrl(){
		return url;
	}

	public String getHtml(){
		return html;
	}

	public void setHtml(String html){
		this.html = html;
	}

	public ImageDto getImage(){
		return image;
	}

	public void setImage(ImageDto image){
		this.image = image;
	}

	@Override
	public String toString(){
		return "CrawlResultDto{" +
				       "title='" + title + '\'' +
				       ", url='" + url + '\'' +
				       ", html='" + html + '\'' +
				       ", image=" + image +
				       ", timestamp=" + timestamp +
				       '}';
	}


}
