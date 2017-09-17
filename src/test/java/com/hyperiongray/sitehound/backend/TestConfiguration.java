package com.hyperiongray.sitehound.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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

public class TestConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestConfiguration.class);
}
