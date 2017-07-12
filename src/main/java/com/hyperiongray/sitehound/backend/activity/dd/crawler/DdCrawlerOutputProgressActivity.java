package com.hyperiongray.sitehound.backend.activity.dd.crawler;

import com.hyperiongray.framework.kafka.service.Activity;
import com.hyperiongray.sitehound.backend.service.dd.crawler.output.DdCrawlerOutputProgressBrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 28/09/16.
 */

@Component
public class DdCrawlerOutputProgressActivity implements Activity {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdCrawlerOutputProgressActivity.class);

    @Autowired
    private DdCrawlerOutputProgressBrokerService ddCrawlerOutputProgressBrokerService;

    @KafkaListener(topics= "dd-crawler-output-progress")
    public void listen(@Payload String data,
//                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        LOGGER.info("received data:" + data.length() + ", partition:" + partition + ", topic:" + topic);
        LOGGER.debug("received data:" + data + ", partition:" + partition + ", topic:" + topic);
        ddCrawlerOutputProgressBrokerService.process(data, new Semaphore(10000));
    }

}
