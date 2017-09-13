package com.hyperiongray.sitehound.backend.test.service;

import com.hyperiongray.sitehound.backend.service.crawler.searchengine.AbstractCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 7/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class GoogleCrawlerBrokerServiceTest {


	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleCrawlerBrokerServiceTest.class);

	private AbstractCrawlerBrokerService googleCrawlerBrokerService;

	@Before
	public void before() throws IOException {
		LOGGER.info("Starting the context");

	}
	@Test
	public void processTest(){
		Executor executor = Executors.newFixedThreadPool(10);
//		ProducerWrapper broadCrawlerProducer = context.getProducerFactory().getBroadCrawlerProducer();
//		ProducerWrapper producerWrapper = new ProducerWrapper(broadCrawlerProducer "fake-queue", 10);
		Semaphore semaphore = new Semaphore(5);

//		googleCrawlerBrokerService.getDdTrainerInputStart(inputString, semaphore);

	}
}
