package com.hyperiongray.sitehound.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by tomas on 7/20/15.
 */

@org.springframework.context.annotation.Configuration
@PropertySources({
		@PropertySource("file:config/properties/app.properties"),
		@PropertySource("file:config/properties/kafka.properties"),
		@PropertySource("file:config/properties/proxy.properties"),
		@PropertySource("file:config/properties/splash.properties"),
		@PropertySource("file:config/properties/tor.properties"),
		@PropertySource("file:config/properties/mongo.properties"),
		@PropertySource("file:config/properties/tika.custom.properties"),
		@PropertySource("file:config/properties/scorer.properties"),
		@PropertySource("file:config/properties/elasticsearch.properties"),
		@PropertySource("file:config/properties/excavator.properties"),

		@PropertySource(value = "file:config/properties-override/application-override.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:config/properties-override/splash.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:config/properties-override/proxy.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:config/properties-override/tor.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:config/properties-override/kafka.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:config/properties-override/excavator.properties", ignoreResourceNotFound = true),

		@PropertySource(value = "file:/root/sitehound-backend/config/properties-override/application-override.properties", ignoreResourceNotFound = true),
})
public class Configuration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
