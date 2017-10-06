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

	@Override
	public String toString() {
		return "AnalyzedCrawlResultWrapperDto{" +
				"workspaces=" + workspaces +
				", webType='" + webType + '\'' +
				", result=" + result +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnalyzedCrawlResultWrapperDto that = (AnalyzedCrawlResultWrapperDto) o;

		if (workspaces != null ? !workspaces.equals(that.workspaces) : that.workspaces != null) return false;
		if (webType != null ? !webType.equals(that.webType) : that.webType != null) return false;
		return result != null ? result.equals(that.result) : that.result == null;
	}

	@Override
	public int hashCode() {
		int result1 = workspaces != null ? workspaces.hashCode() : 0;
		result1 = 31 * result1 + (webType != null ? webType.hashCode() : 0);
		result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
		return result1;
	}
}
