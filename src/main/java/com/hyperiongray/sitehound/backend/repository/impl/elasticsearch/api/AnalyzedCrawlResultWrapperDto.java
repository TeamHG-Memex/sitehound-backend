package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import org.codehaus.jackson.annotate.JsonProperty;


public class AnalyzedCrawlResultWrapperDto{


	private AnalyzedCrawlResultDto analyzedCrawlResultDto;

	@JsonProperty("result")
	public AnalyzedCrawlResultDto getAnalyzedCrawlResultDto(){
		return analyzedCrawlResultDto;
	}

	@JsonProperty("result")
	public void setAnalyzedCrawlResultDto(AnalyzedCrawlResultDto result){
		this.analyzedCrawlResultDto = result;
	}

	@Override
	public String toString() {
		return "AnalyzedCrawlResultWrapperDto{" +
				", analyzedCrawlResultDto=" + analyzedCrawlResultDto +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnalyzedCrawlResultWrapperDto that = (AnalyzedCrawlResultWrapperDto) o;
		return analyzedCrawlResultDto != null ? analyzedCrawlResultDto.equals(that.analyzedCrawlResultDto) : that.analyzedCrawlResultDto == null;
	}

	@Override
	public int hashCode() {
		int result1 = 31 * (analyzedCrawlResultDto != null ? analyzedCrawlResultDto.hashCode() : 0);
		return result1;
	}
}
