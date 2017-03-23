package com.hyperiongray.sitehound.backend.kafka.dispatcher;

import com.hyperiongray.framework.kafka.service.KafkaListenerProcessor;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.kafka.submitter.AquariumTaskSubmitter;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.MetadataBuilder;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.tor.TorCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.twitter.TwitterCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.events.EventService;
//import org.hyperiongray.googlecrawler.kafka.producer.ProducerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tomas on 10/29/15.
 */
@Service
public class KeywordsCrawlerMessageDispatcher implements KafkaListenerProcessor<SubscriberInput>{

	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsCrawlerMessageDispatcher.class);

	@Autowired private GoogleCrawlerBrokerService keywordGoogleCrawlerBrokerService;
	@Autowired private BingCrawlerBrokerService keywordBingCrawlerBrokerService;
	@Autowired private TwitterCrawlerBrokerService twitterCrawlerBrokerService;
	@Autowired private TorCrawlerBrokerService torCrawlerBrokerService;
//	@Autowired private ProducerBuilder producerBuilder;
	@Autowired private CrawlJobRepository crawlJobRepository;
//	@Autowired private AquariumProducer aquariumProducer;
	@Autowired private AquariumTaskSubmitter aquariumTaskSubmitter;
	@Autowired private EventService eventService;

	private ExecutorService executorService = Executors.newFixedThreadPool(2);


	@Override
	public void process(SubscriberInput subscriberInput) throws IOException {


		CrawlJob crawlJob = new CrawlJob(subscriberInput.getWorkspace(), subscriberInput.getJobId(), subscriberInput.getSource(),
				                                Constants.CrawlType.KEYWORDS, subscriberInput.getnResults(), Constants.CrawlerProvider.valueOf(subscriberInput.getCrawlProvider()));

		boolean jobQueuedStarted = crawlJobRepository.updateJobStatus(crawlJob.getJobId(), Constants.JobStatus.STARTED);
		LOGGER.info("Keywords saved job:" +  subscriberInput.getJobId());

		if(!jobQueuedStarted){
			LOGGER.info("SKIPPING PROCESS REQUEST FOR JOB:" + crawlJob.getJobId());
			return;
		}

		if(subscriberInput.getCrawlProvider().equals("HH_JOOGLE")){
			for(String source : subscriberInput.getCrawlSources()){
				switch(source){
					case "SE":
						executorService.submit(new DispatcherWorker(keywordGoogleCrawlerBrokerService, subscriberInput, aquariumTaskSubmitter, Constants.CrawlType.KEYWORDS));
						executorService.submit(new DispatcherWorker(keywordBingCrawlerBrokerService, subscriberInput, aquariumTaskSubmitter, Constants.CrawlType.KEYWORDS));
						break;
					case "TWITTER":
						executorService.submit(new DispatcherWorker(twitterCrawlerBrokerService, subscriberInput, aquariumTaskSubmitter, Constants.CrawlType.KEYWORDS ));
						break;
					case "TOR":
						torCrawlerBrokerService.process(subscriberInput, aquariumTaskSubmitter, Constants.CrawlType.KEYWORDS);
						break;
					case "DD":
						Metadata metadata = MetadataBuilder.build(subscriberInput, Constants.CrawlType.KEYWORDS, Constants.CrawlEntityType.DD);
						EventInput eventInput = new EventInput();
						eventInput.setAction("start");
						eventInput.setEvent("dd-trainer");
						eventInput.setMetadata(metadata);
						eventService.dispatch(eventInput);
						break;
					default:
						throw new RuntimeException("UNKNOWN SOURCE");
				}
			}
		}
		else{
			throw new RuntimeException("UNKNOWN PROVIDER: " + subscriberInput.getCrawlProvider());
		}
	}

}
