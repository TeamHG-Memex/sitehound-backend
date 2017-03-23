package com.hyperiongray.sitehound.backend;

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
@ContextConfiguration(classes = Configuration.class)
public class ContextTest{

	private static final Logger LOGGER = LoggerFactory.getLogger(ContextTest.class);

	@Test
	public void test() throws IOException, URISyntaxException{

		LOGGER.info("");
		LOGGER.info("-----------------------------------------------------------------------------------------");
		LOGGER.info("Context Loaded successfully!");
		LOGGER.info("-----------------------------------------------------------------------------------------");

	}
}



