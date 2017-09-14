package com.hyperiongray.sitehound.backend.service.crawler.searchengine;

import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.submitter.TaskSubmitter;
import com.hyperiongray.sitehound.backend.model.CrawlTask;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlTaskRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.UowHelper;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import com.hyperiongray.sitehound.backend.service.crawler.dispatcher.CrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.htmlunit.UrlFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 5/20/15.
 */
@Service
public abstract class AbstractCrawlerBrokerService implements CrawlerBrokerService {

	@Autowired private UrlFetcher urlFetcher;
	@Autowired private UowHelper uowHelper;
	@Autowired private CrawlTaskRepository crawlTaskRepository;
	@Autowired private MetadataBuilder metadataBuilder;
	@Autowired private UrlDeduplicator urlDeduplicator;

	@Value( "${crawler.threads}" ) private int threadNumber;

	private JsonMapper jsonMapper = new JsonMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrawlerBrokerService.class);


	protected abstract Crawler getCrawler();

	@Override
	public void process(SubscriberInput subscriberInput, TaskSubmitter taskSubmitter, Constants.CrawlType crawlType){
		try {
			// parse the string from the queue to a searchable url
			Set<String> irrelevantHosts = CrawlerUtils.getHostsFromUrls(subscriberInput.getIrrelevantUrl());
			Set<String> existentUrl = Sets.newHashSet(subscriberInput.getExistentUrl());

			int numberOfResultsRequested = subscriberInput.getnResults();
			int currentIndex = 0;
			int pageSize = getCrawler().getMaxPageSize();
			int currentTarget = 0;
			while(currentTarget < numberOfResultsRequested){

				List<SearchEngineCrawlResult> searchEngineCrawlResults = this.getCrawler().crawl(subscriberInput.getIncluded(), subscriberInput.getExcluded(),
						                                                                           irrelevantHosts, existentUrl, currentIndex, pageSize);

				LOGGER.info("Crawl returned docs: " + searchEngineCrawlResults.size());

				if(searchEngineCrawlResults.size()==0){ //no more search results
					LOGGER.info("Search results are fewer than what requested");
					break;
				}
				List<SearchEngineCrawlResult> crawlResultsFiltered = urlDeduplicator.filterFromExistentResults(subscriberInput.getWorkspace(), crawlType, searchEngineCrawlResults);

				Metadata metadata = metadataBuilder.build(subscriberInput, crawlType, getCrawler().getCrawlerEntityType());

				int nSubmitted = this.submitSearchResults(currentTarget, crawlResultsFiltered, taskSubmitter, metadata, numberOfResultsRequested);
				currentIndex += pageSize;
				currentTarget += nSubmitted;
				LOGGER.info("iterating. Now with: " + currentTarget + "/" + numberOfResultsRequested );
			}
			LOGGER.info("exit iterating  with: " + currentTarget + "/" + numberOfResultsRequested );

			// save the task
			CrawlTask crawlTask = new CrawlTask(subscriberInput.getJobId(), getCrawler().getCrawlerEntityType(),
					                                   currentTarget < numberOfResultsRequested ?  currentTarget: numberOfResultsRequested);

			crawlTaskRepository.save(subscriberInput.getWorkspace(), crawlTask);

			LOGGER.info("Crawler " + getCrawler().getCrawlerEntityType() + " done. Waiting for new tasks");
		} catch (Exception e) {
			LOGGER.error("Crawler " + getCrawler().getCrawlerEntityType() + " ERROR", e);
		}
	}




	private int submitSearchResults(int currentTarget, List<SearchEngineCrawlResult> searchEngineCrawlResults, TaskSubmitter taskSubmitter,
	                                Metadata metadata, int numberOfResultsRequested) throws InterruptedException, IOException{

		LOGGER.info("getting " + searchEngineCrawlResults.size() + " results");
		for(SearchEngineCrawlResult crawlResult : searchEngineCrawlResults){
			if (currentTarget >= numberOfResultsRequested){
				break;
			}
			LOGGER.info("Sending #: " + currentTarget +" with url:" + crawlResult.getUrl());
			taskSubmitter.submit(metadata,crawlResult);
			LOGGER.info("Sent #: " + currentTarget +" with url:" + crawlResult.getUrl());

			/*
			AquariumInput aquariumInput = new AquariumInput(metadata);
			aquariumInput.setUrl(crawlResult.getUrl());
			aquariumInput.setIndex(currentTarget);
			String aquariumInputString;
			try {
				aquariumInputString = jsonMapper.toString(aquariumInput);
				producer.send(aquariumInputString);
				LOGGER.info("Sent #: " + currentTarget +" by " + producer  +" with url:" + aquariumInput.getUrl());
//				LOGGER.info("Sent: " + aquariumInputString);
			} catch (IOException e) {
				LOGGER.error("COULD not parse:" + aquariumInput, e);
			}
			*/
			currentTarget++;
		}
		return currentTarget;
	}

}
