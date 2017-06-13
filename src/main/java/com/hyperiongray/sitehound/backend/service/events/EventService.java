package com.hyperiongray.sitehound.backend.service.events;

import com.hyperiongray.sitehound.backend.kafka.producer.dd.modeler.DdModelerProducer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStartDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStopDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStop;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.kafka.producer.dd.crawler.DdCrawlerInputProducer;
import com.hyperiongray.sitehound.backend.kafka.producer.dd.trainer.DdTrainerInputProducer;
import com.hyperiongray.sitehound.backend.model.EventsType;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.dd.crawler.input.DdCrawlerHintsInputService;
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
    @Autowired private DdCrawlerHintsInputService ddCrawlerHintsInputService;
    @Autowired private DdCrawlerInputProducer ddCrawlerInputProducer;

    @Autowired private CrawlJobRepository crawlJobRepository;

    public void dispatch(EventInput eventInput) throws IOException {

        String eventAsString = eventInput.getEvent();
        EventsType event = EventsType.getEnumFromString(eventAsString);

        LOGGER.info("received event:" + eventAsString);
        switch (event){

            case DD_MODELER:
                DdModelerInput ddModelerInput = ddModelerInputService.getDdModelerInput(eventInput.getWorkspaceId());
                ddModelerProducer.submit(ddModelerInput);
                break;

            case DD_TRAINER:
                if (eventInput.getAction().equals("start")){
                    DdTrainerInputStart ddTrainerInputStart = ddTrainerInputService.getDdTrainerInputStart(eventInput.getWorkspaceId());
                    ddTrainerInputProducer.submit(ddTrainerInputStart);
                }
                else if (eventInput.getAction().equals("stop")){
                    DdTrainerInputStop ddTrainerInputStop = ddTrainerInputService.getDdTrainerInputStop(eventInput.getWorkspaceId());
                    ddTrainerInputProducer.submit(ddTrainerInputStop);
                }
                else{
                    throw new UnsupportedOperationException();
                }
                break;

            case DD_CRAWLER:
                if (eventInput.getAction().equals("start")){

                    DdCrawlerInputStartDto ddCrawlerInputStartDto = ddCrawlerInputService.getDdCrawlerInputStart(eventInput);

                    boolean jobQueuedStarted = crawlJobRepository.updateJobStatus(ddCrawlerInputStartDto.getId(), Constants.JobStatus.STARTED);
                    LOGGER.info("Saved broadcrawl dd-crawler start job: " + ddCrawlerInputStartDto.getId());

                    if(!jobQueuedStarted){
                        LOGGER.info("SKIPPING broadcrawl dd-crawler start job: " + ddCrawlerInputStartDto.getId());
                        return;
                    }
                    ddCrawlerInputProducer.submit(ddCrawlerInputStartDto);
                }
                else if (eventInput.getAction().equals("stop")){
//                    boolean jobQueuedStarted = crawlJobRepository.updateJobStatus(ddCrawlerInputStartDto.getId(), Constants.JobStatus.STOPPED);
//                    LOGGER.info("Saved broadcrawl dd-crawler start job: " + ddCrawlerInputStartDto.getId());
//
//                    if(!jobQueuedStarted){
//                        LOGGER.info("SKIPPING broadcrawl dd-crawler start job: " + ddCrawlerInputStartDto.getId());
//                        return;
//                    }
                    DdCrawlerInputStopDto ddCrawlerInputStopDto = ddCrawlerInputService.getDdCrawlerInputStop(eventInput.getWorkspaceId());
                    ddCrawlerInputProducer.submit(ddCrawlerInputStopDto);
                }
                else{
                    throw new UnsupportedOperationException();
                }
                break;

            case BOOKMARK:
                /// action is always "changed"
                ddCrawlerHintsInputService.execute(eventInput);
                break;

            default:
                throw new UnsupportedOperationException();

        }
    }

}
