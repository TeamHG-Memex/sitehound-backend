package com.hyperiongray.sitehound.backend.kafka.producer.dd.crawler;

import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 29/09/16.
 */
@Component
public class DdCrawlerInputProducer {

    @Autowired
    private LocalQueueProducer localQueueProducer;

    private String inputQueue = Queues.DD_CRAWLER_INPUT.getSubscriberTopic();
    private JsonMapper jsonMapper = new JsonMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(DdCrawlerInputProducer.class);

    public void submit(DdCrawlerInputStart ddCrawlerInputStart) throws IOException {
        String message = jsonMapper.toString(ddCrawlerInputStart);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent "  + inputQueue );
        LOGGER.debug("Sent "  + inputQueue +" with message:" + message);
    }

    public void submit(DdCrawlerInputStop ddCrawlerInputStop) throws IOException {
        String message = jsonMapper.toString(ddCrawlerInputStop);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent "  + inputQueue );
        LOGGER.debug("Sent "  + inputQueue +" with message:" + message);
    }

}
