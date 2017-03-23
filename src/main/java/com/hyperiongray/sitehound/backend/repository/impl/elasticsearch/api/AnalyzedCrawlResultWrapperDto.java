package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import java.util.Set;

/**
 * Created by tomas on 2/19/16.
 */
public class AnalyzedCrawlResultWrapperDto{
	private Set<String> workspaces;
	private String webType;
	private AnalyzedCrawlResultDto result;

	public Set<String> getWorkspaces(){
		return workspaces;
	}

	public void setWorkspaces(Set<String> workspaces){
		this.workspaces = workspaces;
	}

	public String getWebType(){
		return webType;
	}

	public void setWebType(String webType){
		this.webType = webType;
	}

	public AnalyzedCrawlResultDto getResult(){
		return result;
	}

	public void setResult(AnalyzedCrawlResultDto result){
		this.result = result;
	}
}
