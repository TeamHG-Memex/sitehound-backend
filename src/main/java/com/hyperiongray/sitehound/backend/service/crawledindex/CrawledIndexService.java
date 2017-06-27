package com.hyperiongray.sitehound.backend.service.crawledindex;

import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.KeywordsRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by tomas on 2/19/16.
 */
@Service
public class CrawledIndexService{

	@Autowired private KeywordsRepository keywordsRepository;
	@Autowired private CrawledIndexHttpRepository crawledIndexHttpRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CrawledIndexService.class);


	public List<TrainedCrawledUrl> getTrainedDocuments(String workspaceId) throws IOException{
		List<TrainedCrawledUrl> trainedDocuments = keywordsRepository.getTrainedDocuments(workspaceId);
		for(TrainedCrawledUrl trainedCrawledUrl: trainedDocuments){
			AnalyzedCrawlResultDto analyzedCrawlResultDto = crawledIndexHttpRepository.get(trainedCrawledUrl.getUrl());
			String text = analyzedCrawlResultDto.getText() == null ? "" : analyzedCrawlResultDto.getText();
			trainedCrawledUrl.setContent(text);
		}
		return trainedDocuments;
	}
}
