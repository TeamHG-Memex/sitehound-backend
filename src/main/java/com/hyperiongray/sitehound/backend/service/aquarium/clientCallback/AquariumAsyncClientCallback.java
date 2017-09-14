package com.hyperiongray.sitehound.backend.service.aquarium.clientCallback;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumAsyncClientCallback.class);

	private final String url;
	private final Semaphore semaphore;
	private final BaseCallbackServiceWrapper baseCallbackServiceWrapper;
	private JsonMapper<AquariumInternal> aquariumInternalJsonMapper = new JsonMapper();


	public AquariumAsyncClientCallback(String url, Semaphore semaphore, BaseCallbackServiceWrapper baseCallbackServiceWrapper){
		this.url=url;
		this.semaphore=semaphore;
		this.baseCallbackServiceWrapper=baseCallbackServiceWrapper;
	}

	public void completed(final Content content) {
		try{
			LOGGER.info("Aquarium response for: " + url);
			AquariumInternal aquariumInternal = aquariumInternalJsonMapper.toObject(content.asString(Charset.forName("UTF-8")), AquariumInternal.class);
			baseCallbackServiceWrapper.execute(url, aquariumInternal);
		}
		catch(Exception e){
			LOGGER.error("Error In Callback", e);
		}
		finally{
			LOGGER.info("Aquarium finished and inserted: " + url);
			semaphore.release();
			LOGGER.info("semaphores available permits: " + semaphore.availablePermits());
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

