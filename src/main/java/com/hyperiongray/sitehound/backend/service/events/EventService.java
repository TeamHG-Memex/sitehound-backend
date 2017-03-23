package com.hyperiongray.sitehound.backend.service.events;

import com.hyperiongray.sitehound.backend.kafka.producer.dd.modeler.DdModelerProducer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStop;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStop;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.kafka.producer.dd.crawler.DdCrawlerInputProducer;
import com.hyperiongray.sitehound.backend.kafka.producer.dd.trainer.DdTrainerInputProducer;
import com.hyperiongray.sitehound.backend.model.DdEventsType;
import com.hyperiongray.sitehound.backend.service.dd.crawler.input.DdCrawlerInputService;
import com.hyperiongray.sitehound.backend.service.dd.modeler.input.DdModelerInputService;
import com.hyperiongray.sitehound.backend.service.dd.trainer.input.DdTrainerInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    @Autowired private DdModelerInputService ddModelerInputService;
    @Autowired private DdModelerProducer ddModelerProducer;

    @Autowired private DdTrainerInputService ddTrainerInputService;
    @Autowired private DdTrainerInputProducer ddTrainerInputProducer;

    @Autowired private DdCrawlerInputService ddCrawlerInputService;
    @Autowired private DdCrawlerInputProducer ddCrawlerInputProducer;

    public void dispatch(EventInput eventInput) throws IOException {

        String eventAsString = eventInput.getEvent();
        DdEventsType event = DdEventsType.getEnumFromString(eventAsString);

        LOGGER.info("received event:" + eventAsString);
        switch (event){

            case DD_MODELER:
                DdModelerInput ddModelerInput = ddModelerInputService.getDdModelerInput(eventInput.getMetadata().getWorkspace());
                ddModelerProducer.submit(ddModelerInput);
                break;

            case DD_TRAINER:
                if (eventInput.getAction().equals("start")){
                    DdTrainerInputStart ddTrainerInputStart = ddTrainerInputService.getDdTrainerInputStart(eventInput.getMetadata().getWorkspace());
                    ddTrainerInputProducer.submit(ddTrainerInputStart);
                }
                else if (eventInput.getAction().equals("stop")){
                    DdTrainerInputStop ddTrainerInputStop = ddTrainerInputService.getDdTrainerInputStop(eventInput.getMetadata().getWorkspace());
                    ddTrainerInputProducer.submit(ddTrainerInputStop);
                }
                else{
                    throw new UnsupportedOperationException();
                }
                break;

            case DD_CRAWLER:
                if (eventInput.getAction().equals("start")){

                    DdCrawlerInputStart ddCrawlerInputStart = ddCrawlerInputService.getDdCrawlerInputStart(eventInput.getMetadata().getWorkspace());
                    ddCrawlerInputProducer.submit(ddCrawlerInputStart);
                }

                else if (eventInput.getAction().equals("stop")){
                    DdCrawlerInputStop ddCrawlerInputStop = ddCrawlerInputService.getDdCrawlerInputStop(eventInput.getMetadata().getWorkspace());
                    ddCrawlerInputProducer.submit(ddCrawlerInputStop);
                }
                else{
                    throw new UnsupportedOperationException();
                }
                break;

            default:
                throw new UnsupportedOperationException();

        }
    }

}
