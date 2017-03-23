package com.hyperiongray.sitehound.backend.kafka.dispatcher;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.submitter.TaskSubmitter;
import com.hyperiongray.sitehound.backend.service.crawler.Constants.CrawlType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 10/30/15.
 */
public class DispatcherWorker implements Runnable{

	private CrawlerBrokerService crawlerBrokerService;
	private final SubscriberInput subscriberInput;
	private final TaskSubmitter taskSubmitter;
	private CrawlType crawlType;

	private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherWorker.class);

	public DispatcherWorker(CrawlerBrokerService crawlerBrokerService, SubscriberInput subscriberInput, TaskSubmitter taskSubmitter, CrawlType crawlType){
		this.crawlerBrokerService=crawlerBrokerService;
		this.subscriberInput=subscriberInput;
		this.taskSubmitter = taskSubmitter;
		this.crawlType = crawlType;
	}

	@Override
	public void run(){
		LOGGER.info("processing message for brokerService: " + crawlerBrokerService);
		crawlerBrokerService.process(subscriberInput, taskSubmitter, crawlType);
	}
}