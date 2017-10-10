package com.hyperiongray.sitehound.backend.kafka.producer.dd.crawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStopDto;
import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStartDto;
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

    public void submit(DdCrawlerInputStartDto ddCrawlerInputStartDto) throws IOException {
        String message = jsonMapper.toJson(ddCrawlerInputStartDto);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent "  + inputQueue + " With ddCrawlerInputStartDto: " + ddCrawlerInputStartDto);
        LOGGER.debug("Sent "  + inputQueue +" with message length:" + message.length());
    }

    public void submit(DdCrawlerInputStopDto ddCrawlerInputStopDto) throws IOException {
        String message = jsonMapper.toJson(ddCrawlerInputStopDto);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent "  + inputQueue + " with ddCrawlerInputStopDto: " + ddCrawlerInputStopDto);
        LOGGER.debug("Sent "  + inputQueue +" with message length:" + message.length());
    }

}
