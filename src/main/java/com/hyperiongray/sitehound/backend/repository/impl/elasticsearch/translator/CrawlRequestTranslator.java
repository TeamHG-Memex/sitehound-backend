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
		crawlRequestDto.setCrawlEntityType(metadata.getCrawlEntityType());
		crawlRequestDto.setCrawlType(metadata.getCrawlType());
		crawlRequestDto.setJobId(metadata.getJobId());
//		crawlRequestDto.setTimestamp(metadata.getTimestamp());
		crawlRequestDto.setUow(metadata.getUow());
		crawlRequestDto.setWorkspace(metadata.getWorkspace());
		crawlRequestDto.setKeywordSourceType(metadata.getKeywordSourceType());
		return crawlRequestDto;
	}
}
