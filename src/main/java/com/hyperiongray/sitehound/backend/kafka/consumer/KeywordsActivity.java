package com.hyperiongray.sitehound.backend.kafka.consumer;

import com.hyperiongray.framework.kafka.service.Activity;
import com.hyperiongray.sitehound.backend.service.crawler.dispatcher.KeywordsCrawlerMessageDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KeywordsActivity implements Activity {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsActivity.class);

	@Autowired private KeywordsCrawlerMessageDispatcher keywordsCrawlerMessageDispatcher;

	@KafkaListener(topics= "google-keywords-input", containerFactory = "kafkaListenerContainerFactory")
	public void listen(@Payload String data,
					   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
					   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		LOGGER.info("received data:" + data.length() + ", partition:" + partition + ", topic:" + topic);
		LOGGER.debug("received data:" + data + ", partition:" + partition + ", topic:" + topic);
		keywordsCrawlerMessageDispatcher.process(data);
	}
}
