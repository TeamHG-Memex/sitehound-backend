package com.hyperiongray.sitehound.backend.kafka.producer.dd.crawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerHintsInputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStartDto;
import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 29/09/16.
 */
@Component
public class DdCrawlerHintsInputProducer {

    @Autowired
    private LocalQueueProducer localQueueProducer;

    private String inputQueue = Queues.DD_CRAWLER_HINTS_INPUT.getSubscriberTopic();
    private JsonMapper jsonMapper = new JsonMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(DdCrawlerHintsInputProducer.class);

    public void submit(DdCrawlerHintsInputDto ddCrawlerHintsInputDto) throws IOException {
        String message = jsonMapper.toString(ddCrawlerHintsInputDto);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent "  + inputQueue );
        LOGGER.debug("Sent "  + inputQueue +" with message:" + message);
    }

}
