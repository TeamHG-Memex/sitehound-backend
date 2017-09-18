package com.hyperiongray.sitehound.backend.activity;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumBrokerService;
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
 * Created by tomas on 9/18/15.
 */
@Component
public class AquariumActivity  {
	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumActivity.class);

	@Autowired private AquariumBrokerService aquariumBrokerService;

	private final JsonMapper<AquariumInput> aquariumInputJsonMapper = new JsonMapper();

	@KafkaListener(topics= "aquarium-input")

	public void listen(@Payload String data,
//					   @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
					   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
					   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

		LOGGER.info("received data:" + data.length() + ", partition:" + partition + ", topic:" + topic);
		LOGGER.debug("received data:" + data + ", partition:" + partition + ", topic:" + topic);
		try {
			AquariumInput aquariumInput = aquariumInputJsonMapper.toObject(data, AquariumInput.class);
			aquariumBrokerService.process(aquariumInput);
		}catch(IOException e){
			LOGGER.error("ERROR PROCESSING: " +  data, e);
		}

	}


}


