package com.hyperiongray.sitehound.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by tomas on 7/20/15.
 */
@SpringBootApplication
@org.springframework.context.annotation.Configuration
@ComponentScan(value={
		"com.hyperiongray.framework",
		"com.hyperiongray.sitehound.backend.activity",
		"com.hyperiongray.sitehound.backend.config",
		"com.hyperiongray.sitehound.backend.httpclient",
		"com.hyperiongray.sitehound.backend.kafka",
		"com.hyperiongray.sitehound.backend.model",
		"com.hyperiongray.sitehound.backend.repository",
		"com.hyperiongray.sitehound.backend.service",
})

public class Application implements CommandLineRunner{
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		System.setProperty("jsse.enableSNIExtension", "false");
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		LOGGER.info("------------- Listening to topics -------");
	}

}
