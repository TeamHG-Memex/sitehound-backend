package com.hyperiongray.sitehound.backend.service;

import com.hyperiongray.framework.kafka.service.KafkaListenerProcessor;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.importurl.ImportUrlInput;
import com.hyperiongray.sitehound.backend.kafka.producer.AquariumProducer;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by tomas on 11/5/15.
 */
@Service
public class ImportUrlListenerService implements KafkaListenerProcessor<ImportUrlInput> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImportUrlListenerService.class);

	@Autowired private AquariumProducer aquariumProducer;

	public void process(ImportUrlInput importUrlInput) throws IOException {
		Metadata metadata = importUrlInput.getMetadata();
		metadata.setCrawlType(Constants.CrawlType.KEYWORDS);
		metadata.setKeywordSourceType(Constants.KeywordSourceType.MANUAL);

		if(importUrlInput.getUrl().contains(".onion")){
			metadata.setCrawlEntityType(Constants.CrawlEntityType.TOR);
			metadata.setKeywordSearchWeb(Constants.KeywordSearchWeb.DARKNET);
		}
		else{
			metadata.setCrawlEntityType(Constants.CrawlEntityType.MANUAL);
			metadata.setKeywordSearchWeb(Constants.KeywordSearchWeb.CLEARNET);
		}

		metadata.setnResults(1);

		AquariumInput aquariumInput = new AquariumInput(metadata);
		aquariumInput.setUrl(importUrlInput.getUrl());
		aquariumInput.setIndex(100);

		aquariumProducer.submit(aquariumInput);
		LOGGER.info("Sent to aquarium-input:" + importUrlInput);
	}
}
