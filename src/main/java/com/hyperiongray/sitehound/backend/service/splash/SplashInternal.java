package com.hyperiongray.sitehound.backend.service.splash;

import java.util.List;

/**
 * Created by tomas on 8/23/15.
 */
public class SplashInternal {

	private String requestedUrl;
	private List<Integer> geometry;
	private String png;
	private String title;
	private String url;

	public String getRequestedUrl() {
		return requestedUrl;
	}

	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;
	}

	public List<Integer> getGeometry() {
		return geometry;
	}

	public void setGeometry(List<Integer> geometry) {
		this.geometry = geometry;
	}

	public String getPng() {
		return png;
	}

	public void setPng(String png) {
		this.png = png;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}
}