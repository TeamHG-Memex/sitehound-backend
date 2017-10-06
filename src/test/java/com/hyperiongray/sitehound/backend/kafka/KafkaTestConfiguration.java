package com.hyperiongray.sitehound.backend.kafka;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by tomas on 7/09/17.
 */

@org.springframework.context.annotation.Configuration
@ComponentScan(value={
        "com.hyperiongray.framework.kafka",
//        "com.hyperiongray.sitehound.backend",
//        "com.hyperiongray.sitehound.backend.config",
        "com.hyperiongray.sitehound.backend.kafka",
        "com.hyperiongray.sitehound.backend.service",
        "com.hyperiongray.sitehound.backend.repository"
})

@PropertySources(value={
        @PropertySource("classpath:src/integration-test/properties/kafka.properties"),
        @PropertySource("classpath:src/integration-test/properties/splash.properties")
})

public class KafkaTestConfiguration {
}
