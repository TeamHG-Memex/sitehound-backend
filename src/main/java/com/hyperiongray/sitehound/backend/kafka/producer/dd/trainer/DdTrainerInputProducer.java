package com.hyperiongray.sitehound.backend.kafka.producer.dd.trainer;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStop;
import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 29/09/16.
 */
@Component
public class DdTrainerInputProducer {

    @Autowired
    private LocalQueueProducer localQueueProducer;

    private String inputQueue = Queues.DD_TRAINER_INPUT.getSubscriberTopic();
    private JsonMapper jsonMapper = new JsonMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerInputProducer.class);

    public void submit(DdTrainerInputStart ddTrainerInputStart) throws IOException {
        String message = jsonMapper.toString(ddTrainerInputStart);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent "  + inputQueue + " with  ddTrainerInputStart: " + ddTrainerInputStart);
        LOGGER.debug("Sent "  + inputQueue +" with message (size):" + message.length());
    }

    public void submit(DdTrainerInputStop ddTrainerInputStop) throws IOException {
//        LOGGER.info("Sent "  + inputQueue );
        String message = jsonMapper.toString(ddTrainerInputStop);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent "  + inputQueue +" with message:" + ddTrainerInputStop);
        LOGGER.debug("Sent "  + inputQueue +" with message:" + message.length());
    }

}
