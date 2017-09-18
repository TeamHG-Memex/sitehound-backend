package com.hyperiongray.sitehound.backend.kafka;

import org.springframework.context.annotation.ComponentScan;

/**
 * Created by tomas on 7/09/17.
 */

@org.springframework.context.annotation.Configuration
@ComponentScan(value={
        "com.hyperiongray.framework.kafka",
        "com.hyperiongray.sitehound.backend",
//        "com.hyperiongray.sitehound.backend.kafka",
////        "com.hyperiongray.sitehound.backend.kafka",
//        "com.hyperiongray.sitehound.backend.kafka.producer",
//        "com.hyperiongray.sitehound.backend.kafka.submitter",
//        "com.hyperiongray.sitehound.backend.service",
//        "com.hyperiongray.sitehound.backend.httpclient",
////        "com.hyperiongray.sitehound.backend.service.aquarium",
////        "com.hyperiongray.sitehound.backend.service.crawler",
////        "com.hyperiongray.sitehound.backend.service.dd",
////        "com.hyperiongray.sitehound.backend.service.nlp",
//        "com.hyperiongray.sitehound.backend.repository",
////        "com.hyperiongray.sitehound.backend.repository.impl.elasticsearch",
////        "com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler",
////        "com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator",
})
public class KafkaTestConfiguration {
}
