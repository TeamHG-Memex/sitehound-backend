package com.hyperiongray.sitehound.backend.service.crawler.tor;

import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.service.crawler.dispatcher.CrawlerBrokerService;
import com.hyperiongray.sitehound.backend.kafka.submitter.TaskSubmitter;
import com.hyperiongray.sitehound.backend.model.CrawlTask;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlTaskRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 5/20/15.
 */
@Service
public class TorCrawlerBrokerService implements CrawlerBrokerService {

	@Autowired private TorCrawler crawler;
	@Autowired private CrawlTaskRepository crawlTaskRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(TorCrawlerBrokerService.class);

	@Override
	public void process(SubscriberInput subscriberInput, TaskSubmitter taskSubmitter, Constants.CrawlType crawlType){

		try{

			Set<String> existentUrl = Sets.newHashSet(subscriberInput.getExistentUrl());
			Set<String> irrelevantHosts = CrawlerUtils.getHostsFromUrls(subscriberInput.getIrrelevantUrl());

			List<TorCrawlerResult> crawlResults = null;

			int batchSize = 20;
			int requestedResults = subscriberInput.getnResults() < 100 ? 100 : subscriberInput.getnResults();

			int remainingRequestedResults = requestedResults;
			int startingFrom = 0;
			int toRequest;

			while(remainingRequestedResults > 0){
				toRequest = requestedResults < batchSize ? requestedResults : batchSize;

				LOGGER.info("requesting startingFrom:" + startingFrom + ", toRequest:" + toRequest + " from requestedResults:" + requestedResults);
				try{
					crawlResults = crawler.crawl(subscriberInput.getIncluded(), subscriberInput.getExcluded(), irrelevantHosts, existentUrl, startingFrom, toRequest);
				}catch(IOException | URISyntaxException e){
					LOGGER.error("TOR CRAWLER ERROR", e);
				}catch(Exception e){
					LOGGER.error("FAILED requesting startingFrom:" + startingFrom + ", toRequest:" + toRequest + " from requestedResults:" + requestedResults, e);
					LOGGER.error("TOR CRAWLER EXCEPTION", e);
				}
				if(crawlResults.size() == 0){
					break;
				}
				submitResults(subscriberInput, taskSubmitter, crawlResults, crawlType);

				remainingRequestedResults -= toRequest;
				startingFrom += toRequest;
			}

			// save the task
			CrawlTask crawlTask = new CrawlTask(subscriberInput.getJobId(), crawler.getCrawlerEntityType(), crawlResults.size());
			crawlTaskRepository.save(subscriberInput.getWorkspace(), crawlTask);

		}
		catch(Exception e){
			LOGGER.error("error in torBrokerService", e);
		}

	}

	private void submitResults(SubscriberInput subscriberInput, TaskSubmitter taskSubmitter, List<TorCrawlerResult> crawlResults, Constants.CrawlType crawlType) throws IOException{
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

		int index=0;

		for(TorCrawlerResult torCrawlerResult: crawlResults){

			taskSubmitter.submit(metadata, torCrawlerResult);
		}
	}
}
