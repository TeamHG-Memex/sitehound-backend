package com.hyperiongray.sitehound.backend.service.aquarium.clientCallback;

import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.CallbackServiceWrapper;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumAsyncClientCallback.class);

	private final String url;
	private final Semaphore semaphore;
	private final CallbackServiceWrapper callbackServiceWrapper;
	private JsonMapper<AquariumInternal> aquariumInternalJsonMapper = new JsonMapper();


	public AquariumAsyncClientCallback(String url, Semaphore semaphore, CallbackServiceWrapper callbackServiceWrapper){
		this.url=url;
		this.semaphore=semaphore;
		this.callbackServiceWrapper = callbackServiceWrapper;
	}

	public void completed(final Content content) {
		LOGGER.info("Aquarium response for: " + url);
		try{
			AquariumInternal aquariumInternal = aquariumInternalJsonMapper.toObject(content.asString(Charset.forName("UTF-8")), AquariumInternal.class);
			callbackServiceWrapper.execute(url, aquariumInternal);
			semaphore.release();
			LOGGER.info("Aquarium finished and inserted: " + url + ". Semaphores available permits: " + semaphore.availablePermits());
		}
		catch(Exception e){
			LOGGER.error("Error In Callback", e);
			semaphore.release();
		}
	}

	public void failed(final Exception ex) {
		LOGGER.error("request failed: " + url, ex);
		semaphore.release();
	}

	public void cancelled() {
		LOGGER.error("request cancelled: " + url);
		semaphore.release();
	}
}

