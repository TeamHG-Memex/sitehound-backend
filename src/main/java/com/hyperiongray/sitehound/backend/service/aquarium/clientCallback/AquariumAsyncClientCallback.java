package com.hyperiongray.sitehound.backend.service.aquarium.clientCallback;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.BaseCallbackServiceWrapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.concurrent.FutureCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 9/20/15.
 */
public class AquariumAsyncClientCallback implements FutureCallback<Content>{

	private final AquariumInput aquariumInput;
	private final Semaphore semaphore;
	private final BaseCallbackServiceWrapper baseCallbackServiceWrapper;
	private JsonMapper<AquariumInternal> aquariumInternalJsonMapper = new JsonMapper();
//	private BaseAquariumCallbackService aquariumCallbackService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumAsyncClientCallback.class);

	public AquariumAsyncClientCallback(AquariumInput aquariumInput, Semaphore semaphore, BaseCallbackServiceWrapper baseCallbackServiceWrapper){
		this.aquariumInput=aquariumInput;
		this.semaphore=semaphore;
		this.baseCallbackServiceWrapper = baseCallbackServiceWrapper;
//		this.aquariumCallbackService = aquariumCallbackService;
	}

	public void completed(final Content content) {

		try{
			LOGGER.info("Aquarium response for: " + aquariumInput.getUrl() + aquariumInput);
			AquariumInternal aquariumInternal = aquariumInternalJsonMapper.toObject(content.asString(Charset.forName("UTF-8")), AquariumInternal.class);
//			aquariumCallbackService.process(aquariumInput, aquariumInternal);
			baseCallbackServiceWrapper.execute(aquariumInput, aquariumInternal);
		}
		catch(Exception e){
			LOGGER.error("Error In Callback", e);
		}
		finally{
			LOGGER.info("Aquarium finished and inserted: " + aquariumInput.getUrl());
			semaphore.release();
			LOGGER.info("semaphores available permits: " + semaphore.availablePermits());
		}
	}

	public void failed(final Exception ex) {
		LOGGER.error("request failed: " + aquariumInput.getUrl(), ex);
		semaphore.release();
	}


	public void cancelled() {
		LOGGER.error("request cancelled: " + aquariumInput.getUrl());
		semaphore.release();
	}
}

