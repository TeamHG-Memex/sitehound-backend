package com.hyperiongray.sitehound.backend.kafka.producer.dd.modeler;

import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.input.DdModelerInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 28/09/16.
 */

@Component
public class DdModelerProducer {

    @Autowired private LocalQueueProducer localQueueProducer;

    private String inputQueue = Queues.DD_MODELER_INPUT.getSubscriberTopic();
    private JsonMapper jsonMapper = new JsonMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerProducer.class);

    public void submit(DdModelerInput ddModelerInput) throws IOException {
        String message = jsonMapper.toString(ddModelerInput);
        localQueueProducer.send(inputQueue, message);
        LOGGER.info("Sent to dd-modeler: "  + inputQueue +" with message length:" + message.length());
    }

}