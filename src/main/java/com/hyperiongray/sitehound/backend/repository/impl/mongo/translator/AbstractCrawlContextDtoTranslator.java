package com.hyperiongray.sitehound.backend.repository.impl.mongo.translator;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlContextDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tomas on 2/12/16.
 */
@Service
public class AbstractCrawlContextDtoTranslator{

	public Map<String, Object> translate(CrawlContextDto crawlContextDto){
		Map<String, Object> document = Maps.newHashMap();
		document.put("workspaceId", crawlContextDto.getCrawlRequestDto().getWorkspace());
		document.put("jobId", crawlContextDto.getCrawlRequestDto().getJobId());
		document.put("timestamp", crawlContextDto.getCrawlRequestDto().getTimestamp());
		document.put("provider", Constants.CrawlerProvider.HH_JOOGLE.toString());
		document.put("crawlEntityType", crawlContextDto.getCrawlRequestDto().getCrawlEntityType().toString());
		document.put("url", crawlContextDto.getCrawlRequestDto().getUrl().toLowerCase());
		document.put("host", crawlContextDto.getAnalyzedCrawlResultDto().getCrawlResultDto().getHost().toLowerCase());
		document.put("language", crawlContextDto.getAnalyzedCrawlResultDto().getLanguage()); // this is also stored in Elasticsearch
		document.put("categories", crawlContextDto.getAnalyzedCrawlResultDto().getCategories());  // this is also stored in Elasticsearch
		document.put("words", crawlContextDto.getAnalyzedCrawlResultDto().getWords());  // this is also stored in Elasticsearch
		document.put("title", crawlContextDto.getAnalyzedCrawlResultDto().getCrawlResultDto().getTitle());  // this is also stored in Elasticsearch
		return document;
	}
}
