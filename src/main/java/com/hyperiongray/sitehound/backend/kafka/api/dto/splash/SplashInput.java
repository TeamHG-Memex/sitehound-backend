package com.hyperiongray.sitehound.backend.kafka.api.dto.splash;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 * Created by tomas on 8/23/15.
 */
public class SplashInput extends KafkaDto{

	private String url;
	private Long timestamp;
	private String strTimestamp;
	private String workspace;
	private String source;
	private String callbackQueue;
	private String collection;
	private String splash_url_path;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getStrTimestamp() {
		return strTimestamp;
	}

	public void setStrTimestamp(String strTimestamp) {
		this.strTimestamp = strTimestamp;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCallbackQueue() {
		return callbackQueue;
	}

	public void setCallbackQueue(String callbackQueue) {
		this.callbackQueue = callbackQueue;
	}


	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getSplash_url_path(){
		return splash_url_path;
	}

	public void setSplash_url_path(String splash_url_path){
		this.splash_url_path=splash_url_path;
	}

//	public String getSplash_url_path() {
//		return splash_url_path;
//	}
//
//	public void setSplash_url_path(String splash_url_path) {
//		this.splash_url_path = splash_url_path;
//	}
}
