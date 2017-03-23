package com.hyperiongray.sitehound.backend.service.crawler.twitter;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.dispatcher.CrawlerBrokerService;
import com.hyperiongray.sitehound.backend.model.CrawlTask;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlTaskRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.UowHelper;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.kafka.submitter.TaskSubmitter;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.nlp.WordCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 7/7/15.
 */
@Service
public class TwitterCrawlerBrokerService implements CrawlerBrokerService {

	@Autowired private TwitterSearchCrawler crawler;
	@Autowired private WordCounter wordCounter = new WordCounter();
	@Autowired private UowHelper uowHelper;
	@Autowired private CrawlTaskRepository crawlTaskRepository;

	private JsonMapper<AquariumInput> jsonMapper = new JsonMapper<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterCrawlerBrokerService.class);

	@Override
	public void process(SubscriberInput subscriberInput, TaskSubmitter taskSubmitter, Constants.CrawlType crawlType){

		try {
			Set<String> irrelevantHosts = CrawlerUtils.getHostsFromUrls(subscriberInput.getIrrelevantUrl());
			Set<String> existentUrl = CrawlerUtils.getHostsFromUrls(subscriberInput.getExistentUrl());
			int n = subscriberInput.getnResults();

			List<TwitterCrawlResult> crawlResults = crawler.crawl(subscriberInput.getIncluded(), subscriberInput.getExcluded(), irrelevantHosts, existentUrl, 0, n);

			LOGGER.info("getting " + crawlResults.size() + " results");

			Metadata metadata = new Metadata();
			metadata.setCrawlType(crawlType);
			metadata.setSource(subscriberInput.getSource());
			metadata.setStrTimestamp(subscriberInput.getStrTimestamp());
			metadata.setWorkspace(subscriberInput.getWorkspace());
			metadata.setTimestamp(subscriberInput.getTimestamp());
			metadata.setCallbackQueue(subscriberInput.getCallbackQueue());
			metadata.setJobId(subscriberInput.getJobId());
			metadata.setCrawlEntityType(crawler.getCrawlerEntityType());
			metadata.setnResults(subscriberInput.getnResults());

//			int index=0;
			for(TwitterCrawlResult crawlResult: crawlResults){

				taskSubmitter.submit(metadata, crawlResult);

				/*
				AquariumInput aquariumInput = new AquariumInput(metadata);
				aquariumInput.setUrl(crawlResult.getUrl());
				aquariumInput.setIndex(index);
				index++;

				String aquariumInputString;
				try {
					aquariumInputString = jsonMapper.toString(aquariumInput);
					producer.send(aquariumInputString);
					LOGGER.info("Sent: " + aquariumInput.getUrl());
					LOGGER.info("Sent: " + aquariumInputString);
				} catch (IOException e) {
					LOGGER.error("COULD not parse:" + aquariumInput, e);
				}
				*/
			}

			// save the task
			CrawlTask crawlTask = new CrawlTask(subscriberInput.getJobId(), crawler.getCrawlerEntityType(), crawlResults.size());
			crawlTaskRepository.save(subscriberInput.getWorkspace(), crawlTask);

		} catch (Exception e) {
			LOGGER.error("TWITTER_BROKER_ERROR", e);
		}
	}

}
