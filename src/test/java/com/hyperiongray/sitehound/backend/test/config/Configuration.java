package com.hyperiongray.sitehound.backend.test.config;

import org.springframework.test.context.TestPropertySource;

/**
 * Created by tomas on 7/20/15.
 */

@org.springframework.context.annotation.Configuration


@TestPropertySource(locations = {
		"file:properties/elasticsearch.properties",
})

public class Configuration {

}
