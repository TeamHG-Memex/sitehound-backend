package com.hyperiongray.sitehound.backend.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.TestPropertySource;

/**
 * Created by tomas on 7/20/15.
 */

@org.springframework.context.annotation.Configuration

//@PropertySources({
//		@PropertySource("file:config/properties/app.properties"),
//		@PropertySource("file:config/properties/kafka.properties"),
//		@PropertySource("file:config/properties/proxy.properties"),
//		@PropertySource("file:config/properties/splash.properties"),
//		@PropertySource("file:config/properties/tor.properties"),
//		@PropertySource("file:config/properties/mongo.properties"),
//		@PropertySource("file:config/properties/tika.custom.properties"),
//		@PropertySource("file:config/properties/scorer.properties"),
//		@PropertySource("file:config/properties/elasticsearch.properties"),
//		@PropertySource("file:config/properties/excavator.properties"),
//		@PropertySource("file:config/properties/classifier.properties"),
//})

@TestPropertySource(locations = {
		"file:properties/elasticsearch.properties",
})

public class Configuration {

}
