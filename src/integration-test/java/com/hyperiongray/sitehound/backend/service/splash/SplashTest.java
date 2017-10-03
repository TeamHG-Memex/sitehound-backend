package com.hyperiongray.sitehound.backend.service.splash;

import com.hyperiongray.sitehound.backend.config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by tomas on 6/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class SplashTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SplashTest.class);

	@Autowired
	private Splash splash;

	@Test
	public void testSnapshot() throws IOException{
		String response = splash.snapshot("http://www.hyperiongray.com/");
		LOGGER.info(response);
	}



	@Test
	public void snapshotTest() throws IOException{
		String response = splash.snapshot("https://twitter.com/northquahog48/status/647427369783926785");
		LOGGER.info(response);
	}


}