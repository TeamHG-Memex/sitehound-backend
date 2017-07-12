package com.hyperiongray.sitehound.backend.activity;

import com.hyperiongray.framework.kafka.service.Activity;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.service.events.EventsBrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 28/09/16.
 */
@Component
public class EventsActivity implements Activity {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsActivity.class);

    @Autowired private EventsBrokerService eventsBrokerService;

    private JsonMapper<EventInput> jsonMapper= new JsonMapper();
    @KafkaListener(topics= "events-input")
    public void listen(@Payload String data,
//                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        try{
            LOGGER.info("received data:" + data.length() + ", partition:" + partition + ", topic:" + topic);
            LOGGER.debug("received data:" + data + ", partition:" + partition + ", topic:" + topic);
            EventInput eventInput = jsonMapper.toObject(data, EventInput.class);
            eventsBrokerService.process(eventInput);
        } catch(IOException e){
            LOGGER.error("PARSING ERROR", e);
        }

    }


}

