package com.hyperiongray.sitehound.backend.kafka;

import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.modeler.input.DdModelerInputService;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 2/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KafkaTestConfiguration.class)
@SpringBootTest
public class ContextLoadingTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContextLoadingTest.class);

	@MockBean AquariumAsyncClient aquariumAsyncClient;
	@MockBean MongoRepository mongoRepository;
	@MockBean GoogleCrawlerBrokerService googleCrawlerBrokerService;
	@MockBean BingCrawlerBrokerService bingCrawlerBrokerService;
	@MockBean DdModelerInputService ddModelerInputService;
	@MockBean HttpProxyClientImpl httpProxyClient;
	@MockBean HttpClientConnector httpClientConnector;
	@MockBean TikaService tikaService;

	@Test
	public void test(){

		LOGGER.info("");
		LOGGER.info("-----------------------------------------------------------------------------------------");
		LOGGER.info("Context Loaded successfully!");
		LOGGER.info("-----------------------------------------------------------------------------------------");

	}
}



