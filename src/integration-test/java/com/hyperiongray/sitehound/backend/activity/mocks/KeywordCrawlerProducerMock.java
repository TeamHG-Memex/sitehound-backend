package com.hyperiongray.sitehound.backend.activity.mocks;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KeywordCrawlerProducerMock{
	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordCrawlerProducerMock.class);

	private JsonMapper jsonMapper = new JsonMapper();

	@Autowired
	private LocalQueueProducer localQueueProducer;


	public void submit(SubscriberInput subscriberInput) throws IOException{
		String subscriberInputString = jsonMapper.toString(subscriberInput);
		localQueueProducer.send(Queues.KEYWORDS_INPUT.getSubscriberTopic(), subscriberInputString);
		LOGGER.info("Sent to keywords"  + subscriberInputString +" with url:" + subscriberInput);

	}

}
