package com.hyperiongray.sitehound.backend.service.events;

import com.hyperiongray.framework.kafka.service.KafkaListenerProcessor;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class EventsBrokerService implements KafkaListenerProcessor<EventInput> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsBrokerService.class);

    @Autowired private EventService eventService;

    @Override
    public void process(EventInput eventInput){

        try{
            LOGGER.debug("Receiving response from events: " + eventInput);
            eventService.dispatch(eventInput);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + eventInput, e);
        }
    }
}
