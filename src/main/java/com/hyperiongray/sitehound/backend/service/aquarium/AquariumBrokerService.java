package com.hyperiongray.sitehound.backend.service.aquarium;

import com.hyperiongray.framework.kafka.service.KafkaListenerProcessor;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.BroadcrawlerAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.KeywordsAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DefaultCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.aquarium.clientCallback.AquariumAsyncClientCallback;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.apache.http.client.fluent.ContentResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 9/19/15.
 */
@Service
public class AquariumBrokerService implements KafkaListenerProcessor<AquariumInput>{
	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumBrokerService.class);

	@Autowired private AquariumAsyncClient aquariumClient;
	@Autowired private KeywordsAquariumCallbackService keywordsAquariumCallbackService;
	@Autowired private BroadcrawlerAquariumCallbackService broadcrawlerAquariumCallbackService;

	@Override
	public void process(AquariumInput aquariumInput) throws IOException {

		DefaultCallbackServiceWrapper callbackServiceWrapper;
		if (aquariumInput.getMetadata().getCrawlType().equals(Constants.CrawlType.KEYWORDS)){
			callbackServiceWrapper = new DefaultCallbackServiceWrapper(aquariumInput, keywordsAquariumCallbackService);
		}
		else if(aquariumInput.getMetadata().getCrawlType().equals(Constants.CrawlType.BROADCRAWL)){
			callbackServiceWrapper = new DefaultCallbackServiceWrapper(aquariumInput, broadcrawlerAquariumCallbackService);
		}
		else{
			throw new UnsupportedOperationException();
		}
//		AquariumAsyncClientCallback aquariumAsyncClientCallback = new AquariumAsyncClientCallback(aquariumInput.getUrl(), semaphore, callbackServiceWrapper);

		LOGGER.debug("Aquarium requesting: " + aquariumInput.getUrl());
		aquariumClient.fetch(aquariumInput.getUrl(), callbackServiceWrapper);
		LOGGER.debug("Aquarium requested: " + aquariumInput.getUrl());
	}


}