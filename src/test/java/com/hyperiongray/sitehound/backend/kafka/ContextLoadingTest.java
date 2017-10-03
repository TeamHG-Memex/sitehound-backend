package com.hyperiongray.sitehound.backend.kafka;

import com.hyperiongray.sitehound.backend.config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by tomas on 2/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KafkaTestConfiguration.class)
public class ContextLoadingTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContextLoadingTest.class);

	@Test
	public void test() throws IOException, URISyntaxException{

		LOGGER.info("");
		LOGGER.info("-----------------------------------------------------------------------------------------");
		LOGGER.info("Context Loaded successfully!");
		LOGGER.info("-----------------------------------------------------------------------------------------");

	}
}



