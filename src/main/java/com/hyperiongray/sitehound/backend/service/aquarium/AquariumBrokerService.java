package com.hyperiongray.sitehound.backend.service.aquarium;

import com.hyperiongray.framework.kafka.service.KafkaListenerProcessor;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DefaultAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DefaultCallbackServiceWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by tomas on 9/19/15.
 */
@Service
public class AquariumBrokerService implements KafkaListenerProcessor<AquariumInput>{
	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumBrokerService.class);

	@Autowired private AquariumAsyncClient aquariumClient;
	@Autowired private DefaultAquariumCallbackService defaultAquariumCallbackService;

	@Override
	public void process(AquariumInput aquariumInput) throws IOException {
		DefaultCallbackServiceWrapper callbackServiceWrapper = new DefaultCallbackServiceWrapper(aquariumInput, defaultAquariumCallbackService);
		LOGGER.debug("Aquarium requesting: " + aquariumInput.getUrl());
		aquariumClient.fetch(aquariumInput.getUrl(), callbackServiceWrapper);
		LOGGER.debug("Aquarium requested: " + aquariumInput.getUrl());
	}


}