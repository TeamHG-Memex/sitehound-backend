package com.hyperiongray.sitehound.backend.integration.mocks;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 2/4/16.
 */
@Component
public class KeywordCrawlerProducerMock{


	@Value( "${kafka.subscriber.topic.keywords}" )
	private String keywordsInputQueue;

	private JsonMapper jsonMapper = new JsonMapper();

	@Autowired
	private LocalQueueProducer localQueueProducer;

	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordCrawlerProducerMock.class);

//	@Override
//	protected String getInputQueue(){
//		return keywordsInputQueue;
//	}

	public void submit(SubscriberInput subscriberInput) throws IOException{
		String subscriberInputString = jsonMapper.toString(subscriberInput);
		localQueueProducer.send(keywordsInputQueue, subscriberInputString);
		LOGGER.info("Sent to keywords"  + subscriberInputString +" with url:" + subscriberInput);

	}

//	@Override
//	public void submit(Metadata metadata, String url) throws IOException{
//
//
//
//
//
//
////		String importUrlString = jsonMapper.toString(importUrl);
////		send(importUrlString);
////		LOGGER.info("Sent to aquarium"  + importUrlString +" with url:" + importUrl.getUrl());
//	}


}
