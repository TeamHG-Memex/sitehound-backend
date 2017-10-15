package com.hyperiongray.sitehound.backend.service.events;

import com.hyperiongray.sitehound.backend.kafka.producer.dd.modeler.DdModelerProducer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStartDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStopDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.input.DdModelerInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStop;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.kafka.producer.dd.crawler.DdCrawlerInputProducer;
import com.hyperiongray.sitehound.backend.kafka.producer.dd.trainer.DdTrainerInputProducer;
import com.hyperiongray.sitehound.backend.model.EventsType;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.framework.JsonMapper;
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
//                DdModelerInput ddModelerInput = ddModelerInputService.getDdModelerInput(eventInput.getWorkspaceId());
//                ddModelerProducer.submit(ddModelerInput);
                throw new UnsupportedOperationException();

            case DD_TRAINER:
                if (eventInput.getAction().equals("start")){
//                    DdTrainerInputStart ddTrainerInputStart = ddTrainerInputService.getDdTrainerInputStart(eventInput);
//                    crawlJobRepository.updateJobStatus(ddTrainerInputStart.getId(), Constants.JobStatus.STARTED);
//                    ddTrainerInputProducer.submit(ddTrainerInputStart);
                    throw new UnsupportedOperationException();
                }
                else if (eventInput.getAction().equals("stop")){
//                    DdTrainerInputStop ddTrainerInputStop = ddTrainerInputService.getDdTrainerInputStop(eventInput);
//                    crawlJobRepository.updateJobStatus(ddTrainerInputStop.getId(), Constants.JobStatus.STOPPED);
//                    ddTrainerInputProducer.submit(ddTrainerInputStop);
                    throw new UnsupportedOperationException();

                }
                else if (eventInput.getAction().toLowerCase().equals("finished")){
//                    DdTrainerInputStop ddTrainerInputStop = ddTrainerInputService.getDdTrainerInputStop(eventInput);
//                    crawlJobRepository.updateJobStatus(ddTrainerInputStop.getId(), Constants.JobStatus.FINISHED);
                    throw new UnsupportedOperationException();
                }
                else {
                    throw new UnsupportedOperationException();
                }
//                break;

            case DD_CRAWLER:
                if (eventInput.getAction().equals("start")){

//                    DdCrawlerInputStartDto ddCrawlerInputStartDto = ddCrawlerInputService.getDdCrawlerInputStart(eventInput);
//                    boolean jobQueuedStarted = crawlJobRepository.updateJobStatus(ddCrawlerInputStartDto.getId(), Constants.JobStatus.STARTED);
//                    LOGGER.info("Saved broadcrawl dd-crawler start job: " + ddCrawlerInputStartDto.getId());
//                    if(!jobQueuedStarted){
//                        LOGGER.info("SKIPPING broadcrawl dd-crawler start job: " + ddCrawlerInputStartDto.getId());
//                        return;
//                    }
//                    ddCrawlerInputProducer.submit(ddCrawlerInputStartDto);
                    throw new UnsupportedOperationException();
                }
                else if (eventInput.getAction().equals("stop")){
//                    DdCrawlerInputStopDto ddCrawlerInputStopDto = ddCrawlerInputService.getDdCrawlerInputStop(eventInput.getWorkspaceId());
//                    crawlJobRepository.updateJobStatus(ddCrawlerInputStopDto.getId(), Constants.JobStatus.STOPPED);
//                    ddCrawlerInputProducer.submit(ddCrawlerInputStopDto);
                }
                else if (eventInput.getAction().toLowerCase().equals("finished")){
                    DdTrainerInputStop ddTrainerInputStop = ddTrainerInputService.getDdTrainerInputStop(eventInput);
                    crawlJobRepository.updateJobStatus(ddTrainerInputStop.getId(), Constants.JobStatus.FINISHED);
                }
                else{
                    throw new UnsupportedOperationException();
                }
                break;

            case DD_DEEPCRAWLER:
                if (eventInput.getAction().toLowerCase().equals("finished")){
//                    DdTrainerInputStop ddTrainerInputStop = ddTrainerInputService.getDdTrainerInputStop(eventInput);
                    String jobId = getJobIdFromArgs(eventInput.getArguments());
                    crawlJobRepository.updateJobStatus(jobId, Constants.JobStatus.FINISHED);
                }
                break;
            case BOOKMARK:
                /// action is always "changed"
//                ddCrawlerHintsInputService.execute(eventInput);
                throw new UnsupportedOperationException();
//                break;

            case JOB:
                if (eventInput.getAction().toLowerCase().equals("stop")) {
                    DdTrainerInputStop ddTrainerInputStop = ddTrainerInputService.getDdTrainerInputStop(eventInput);
                    crawlJobRepository.updateJobStatus(ddTrainerInputStop.getId(), Constants.JobStatus.STOPPED);
                }
                break;

                default:
                throw new UnsupportedOperationException();

        }
    }

    private String getJobIdFromArgs(String arguments){
        JsonMapper<JobFinishedArgs> jsonMapper = new JsonMapper();

        JobFinishedArgs jobFinishedArgs = null;
        try {
            jobFinishedArgs = jsonMapper.toObject(arguments, JobFinishedArgs.class);
        } catch (IOException e) {
            LOGGER.error("failed to deserialize:" + arguments);
            e.printStackTrace();
        }
        return jobFinishedArgs.getJobId();
    }

    private static class JobFinishedArgs{
        private String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }
    }

}
