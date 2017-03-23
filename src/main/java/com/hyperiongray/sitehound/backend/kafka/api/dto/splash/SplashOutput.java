package com.hyperiongray.sitehound.backend.kafka.api.dto.splash;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 * Created by tomas on 8/23/15.
 */
public class SplashOutput extends KafkaDto {

	private String url;
	private String snapshot;
	private String workspace;
	private String collection;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}


}