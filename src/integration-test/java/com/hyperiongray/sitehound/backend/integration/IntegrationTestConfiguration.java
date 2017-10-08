package com.hyperiongray.sitehound.backend.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan(value={
		"com.hyperiongray.framework",
		"com.hyperiongray.sitehound.backend.kafka.consumer",
		"com.hyperiongray.sitehound.backend.config",
		"com.hyperiongray.sitehound.backend.service.httpclient",
		"com.hyperiongray.sitehound.backend.kafka",
		"com.hyperiongray.sitehound.backend.model",
		"com.hyperiongray.sitehound.backend.repository",
		"com.hyperiongray.sitehound.backend.service",
})

public class IntegrationTestConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationTestConfiguration.class);
}
