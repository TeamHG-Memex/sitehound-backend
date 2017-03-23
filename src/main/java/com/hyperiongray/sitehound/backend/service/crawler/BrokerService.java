package com.hyperiongray.sitehound.backend.service.crawler;

import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 6/23/15.
 */
public interface BrokerService {

	void process(String jsonInput, Semaphore semaphore);
}
