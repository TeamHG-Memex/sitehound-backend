package com.hyperiongray.sitehound.backend.activity;

import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.dispatcher.KeywordsCrawlerMessageDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Created by tomas on 10/28/15.
 */
@Component
public class KeywordsActivity implements Activity{
	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsActivity.class);

	@Autowired private KeywordsCrawlerMessageDispatcher keywordsCrawlerMessageDispatcher;

	private JsonMapper<SubscriberInput> jsonMapper = new JsonMapper();

	@KafkaListener(topics= "google-keywords-input")
	public void listen(@Payload String data,
//					   @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
					   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
					   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

		try{
			LOGGER.debug("received data:" + data + ", partition:" + partition + ", topic:" + topic);
			SubscriberInput subscriberInput = jsonMapper.toObject(data, SubscriberInput.class);
			keywordsCrawlerMessageDispatcher.process(subscriberInput);
		} catch(IOException e){
			LOGGER.error("PARSING ERROR", e);
		}

	}

}
