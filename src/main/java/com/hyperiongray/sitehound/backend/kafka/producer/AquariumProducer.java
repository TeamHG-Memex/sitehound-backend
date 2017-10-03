package com.hyperiongray.sitehound.backend.kafka.producer;

import com.hyperiongray.framework.kafka.service.KafkaProducerProcessor;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.framework.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 2/4/16.
 */

@Component
public class AquariumProducer implements KafkaProducerProcessor<AquariumInput>{
	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumProducer.class);

	private JsonMapper jsonMapper = new JsonMapper();

	@Autowired private LocalQueueProducer queueProducer;

//	@Deprecated
//	public void submit(Metadata metadata, String url) throws IOException{
//		AquariumInput aquariumInput = new AquariumInput(metadata);
//		aquariumInput.setUrl(url);
//		aquariumInput.setIndex(100);
//		String message = jsonMapper.toString(aquariumInput);
//		queueProducer.send(Queues.AQUARIUM_INPUT.getSubscriberTopic(), message);
//		LOGGER.info("Sent to aquarium"  + Queues.AQUARIUM_INPUT.getSubscriberTopic() +" with url:" + aquariumInput.getUrl());
//	}

	public void submit(AquariumInput aquariumInput) throws IOException{
		String message = jsonMapper.toString(aquariumInput);
		queueProducer.send(Queues.AQUARIUM_INPUT.getSubscriberTopic(), message);
		LOGGER.info("Sent to aquarium"  + Queues.AQUARIUM_INPUT.getSubscriberTopic() +" with url:" + aquariumInput.getUrl());
	}

}
