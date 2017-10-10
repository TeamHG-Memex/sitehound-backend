package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

/**
 * Created by tomas on 2/19/16.
 */

public class AnalyzedCrawlResultWrapperDto{


	private AnalyzedCrawlResultDto analyzedCrawlResultDto;

	public AnalyzedCrawlResultDto getAnalyzedCrawlResultDto(){
		return analyzedCrawlResultDto;
	}

	public void setAnalyzedCrawlResultDto(AnalyzedCrawlResultDto result){
		this.analyzedCrawlResultDto = result;
	}

	@Override
	public String toString() {
		return "AnalyzedCrawlResultWrapperDto{" +
//				"workspaces=" + workspaces +
//				", webType='" + webType + '\'' +
				", analyzedCrawlResultDto=" + analyzedCrawlResultDto +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnalyzedCrawlResultWrapperDto that = (AnalyzedCrawlResultWrapperDto) o;

//		if (workspaces != null ? !workspaces.equals(that.workspaces) : that.workspaces != null) return false;
//		if (webType != null ? !webType.equals(that.webType) : that.webType != null) return false;
		return analyzedCrawlResultDto != null ? analyzedCrawlResultDto.equals(that.analyzedCrawlResultDto) : that.analyzedCrawlResultDto == null;
	}

	@Override
	public int hashCode() {
//		int result1 = workspaces != null ? workspaces.hashCode() : 0;
//		result1 = 31 * result1 + (webType != null ? webType.hashCode() : 0);
		int result1 = 31 * (analyzedCrawlResultDto != null ? analyzedCrawlResultDto.hashCode() : 0);
		return result1;
	}
}
