package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlRequestDto;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 2/11/16.
 */
@Service
public class CrawlRequestTranslator{

	public CrawlRequestDto fromAquariumInput(AquariumInput aquariumInput){
		CrawlRequestDto crawlRequestDto = new CrawlRequestDto(aquariumInput.getUrl());
		crawlRequestDto.setCrawlEntityType(aquariumInput.getMetadata().getCrawlEntityType());
		crawlRequestDto.setCrawlType(aquariumInput.getMetadata().getCrawlType());
		crawlRequestDto.setJobId(aquariumInput.getMetadata().getJobId());
		crawlRequestDto.setTimestamp(aquariumInput.getMetadata().getTimestamp());
		crawlRequestDto.setUow(aquariumInput.getMetadata().getUow());
		crawlRequestDto.setWorkspace(aquariumInput.getMetadata().getWorkspace());
		return crawlRequestDto;
	}
}
