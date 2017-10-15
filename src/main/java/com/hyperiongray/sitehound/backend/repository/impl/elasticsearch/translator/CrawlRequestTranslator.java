package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlRequestDto;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 2/11/16.
 */
@Service
public class CrawlRequestTranslator{

	public CrawlRequestDto fromMetadata(String url, Metadata metadata){
		CrawlRequestDto crawlRequestDto = new CrawlRequestDto(url);
		crawlRequestDto.setWorkspace(metadata.getWorkspace());
		crawlRequestDto.setJobId(metadata.getJobId());
		crawlRequestDto.setCrawlType(metadata.getCrawlType());
		crawlRequestDto.setCrawlEntityType(metadata.getCrawlEntityType());
//		crawlRequestDto.setTimestamp(metadata.getTimestamp());
		crawlRequestDto.setKeywordSourceType(metadata.getKeywordSourceType());
		crawlRequestDto.setKeywordSearchWeb(metadata.getKeywordSearchWeb());
		crawlRequestDto.setUow(metadata.getUow());
		return crawlRequestDto;
	}
}
