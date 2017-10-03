package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import org.springframework.context.annotation.ComponentScan;

/**
 * Created by tomas on 7/09/17.
 */

@org.springframework.context.annotation.Configuration
@ComponentScan(value={
    "com.hyperiongray.sitehound.backend.config",
    "com.hyperiongray.sitehound.backend.repository.impl.elasticsearch",
})
public class ElasticsearchTestConfiguration {
}
