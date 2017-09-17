package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 2/11/16.
 */
@Service
public class AnalyzedCrawlRequestFactory{
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzedCrawlRequestFactory.class);

	@Deprecated
	public AnalyzedCrawlResultDto build(CrawlResultDto crawlResultDto) throws Exception {
		return new AnalyzedCrawlResultDto(crawlResultDto);
	}

}
