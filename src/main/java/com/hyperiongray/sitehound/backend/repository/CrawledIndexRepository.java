package com.hyperiongray.sitehound.backend.repository;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

import java.io.IOException;

/**
 * Created by tomas on 2/9/16.
 */
public interface CrawledIndexRepository{

//	void save(String id, String workspace, Constants.CrawlEntityType crawlEntityType, AnalyzedCrawlResultDto analyzedCrawlResultDto) throws IOException;
	void save(String id, AnalyzedCrawlResultDto analyzedCrawlResultDto) throws IOException;

//	String upsert(String id, String workspace, Constants.CrawlEntityType crawlEntityType, AnalyzedCrawlResultDto analyzedCrawlResultDto) throws IOException;

	AnalyzedCrawlResultDto get(String key) throws IOException;

	void delete(String url) throws IOException;
}
