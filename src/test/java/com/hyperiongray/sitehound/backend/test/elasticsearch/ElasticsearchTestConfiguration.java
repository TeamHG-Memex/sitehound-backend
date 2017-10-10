package com.hyperiongray.sitehound.backend.test.elasticsearch;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

/**
 * Created by tomas on 7/09/17.
 */

@org.springframework.context.annotation.Configuration
@ComponentScan(value={
//    "com.hyperiongray.sitehound.backend.test.config",
    "com.hyperiongray.sitehound.backend.repository.impl.elasticsearch",
})

//@TestPropertySource(locations = {
//        "file:properties/elasticsearch.properties",
//})

public class ElasticsearchTestConfiguration {
}
