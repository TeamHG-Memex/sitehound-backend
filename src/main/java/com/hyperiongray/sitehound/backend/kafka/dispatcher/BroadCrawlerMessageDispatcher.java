package com.hyperiongray.sitehound.backend.kafka.dispatcher;

import com.hyperiongray.framework.kafka.service.KafkaListenerProcessor;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.submitter.AquariumTaskSubmitter;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.tor.TorCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.events.EventService;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.kafka.submitter.TaskSubmitter;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.MetadataBuilder;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.twitter.TwitterCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.ScorerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tomas on 10/29/15.
 */
@Service
public class BroadCrawlerMessageDispatcher implements KafkaListenerProcessor<SubscriberInput> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BroadCrawlerMessageDispatcher.class);

	@Autowired private GoogleCrawlerBrokerService broadcrawlerGoogleCrawlerBrokerService;
	@Autowired private BingCrawlerBrokerService broadcrawlerBingCrawlerBrokerService;
	@Autowired private TwitterCrawlerBrokerService twitterCrawlerBrokerService;
	@Autowired private TorCrawlerBrokerService torCrawlerBrokerService;
	@Autowired private CrawlJobRepository crawlJobRepository;
	@Autowired private ScorerService scorerService;
	@Autowired private AquariumTaskSubmitter aquariumTaskSubmitter;
	@Autowired private EventService eventService;

	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	@Override
	public void process(SubscriberInput subscriberInput){

		try{

			LOGGER.info("Broadcrawl processing job:" +  subscriberInput.getJobId());

//			CrawlJob crawlJob = new CrawlJob(subscriberInput.getWorkspace(), subscriberInput.getJobId(), subscriberInput.getSource(),
//					                                Constants.CrawlType.BROADCRAWL, subscriberInput.getnResults(),
//					                                Constants.CrawlerProvider.valueOf(subscriberInput.getCrawlProvider()));

			boolean jobQueuedStarted = crawlJobRepository.updateJobStatus(subscriberInput.getJobId(), Constants.JobStatus.STARTED);
			LOGGER.info("Broadcrawl saved job:" +  subscriberInput.getJobId());

			if(!jobQueuedStarted){
				LOGGER.info("SKIPPING PROCESS REQUEST FOR JOB:" + subscriberInput.getJobId());
				return;
			}


			for(String source : subscriberInput.getCrawlSources()) {
				if(source.equals("SE") || source.equals("TOR")){
					try{
						scorerService.indexTrainedData(subscriberInput.getWorkspace(), subscriberInput.getJobId());
					}catch(Exception e){
						LOGGER.error("INDEXING ERROR", e);
						throw new RuntimeException("INDEXING ERROR");
					}
					LOGGER.info("Broadcrawl indexed job:" +  subscriberInput.getJobId());
					break;
				}
			}


			if(subscriberInput.getCrawlProvider().equals("HH_JOOGLE")){
				for(String source : subscriberInput.getCrawlSources()){
					switch(source){
						case "SE":
							TaskSubmitter taskSubmitter = aquariumTaskSubmitter;
							executorService.submit(new DispatcherWorker(broadcrawlerGoogleCrawlerBrokerService, subscriberInput, taskSubmitter, Constants.CrawlType.BROADCRAWL));
//							semaphore.acquire(); //acquire one extra since we are splitting the jobs
							executorService.submit(new DispatcherWorker(broadcrawlerBingCrawlerBrokerService, subscriberInput, taskSubmitter, Constants.CrawlType.BROADCRAWL));
							break;
						case "TOR":
							executorService.submit(new DispatcherWorker(torCrawlerBrokerService, subscriberInput, aquariumTaskSubmitter, Constants.CrawlType.BROADCRAWL));
							break;
						case "DD":
//							Metadata metadata = MetadataBuilder.build(subscriberInput, Constants.CrawlType.BROADCRAWL, Constants.CrawlEntityType.DD);
							EventInput eventInput = new EventInput();
							eventInput.setAction("start");
							eventInput.setEvent("dd-crawler");
//							eventInput.setMetadata(metadata);
							eventInput.setWorkspaceId(subscriberInput.getWorkspace());
							eventService.dispatch(eventInput);
							break;

						default:
							throw new RuntimeException("UNKNOWN SOURCE");
					}
				}
			}
			else{
				LOGGER.warn("INVALID PROVIDER: " + subscriberInput.getCrawlProvider());
				throw new RuntimeException("INVALID PROVIDER: " + subscriberInput.getCrawlProvider());
			}
		}
		catch(Exception e){
			LOGGER.error("Job Failed", e);
		}
		catch(Throwable t){
			LOGGER.error("Job Failed", t);
		}
	}

}
