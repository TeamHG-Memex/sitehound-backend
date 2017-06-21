package com.hyperiongray.sitehound.backend.service.dd.trainer.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStop;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawledTrainingRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStart;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
@Service
public class DdTrainerInputService {


    @Autowired private CrawledTrainingRepository crawledTrainingRepository;
    @Autowired private DdRepository ddRepository;

    public DdTrainerInputStart getDdTrainerInputStart(EventInput eventInput) throws IOException {
        DdTrainerInputStart ddTrainerInputStart = new DdTrainerInputStart();

        JsonMapper<DdTrainerInputStartArgs> jsonMapper= new JsonMapper();
        DdTrainerInputStartArgs ddTrainerInputStartArgs = jsonMapper.toObject(eventInput.getArguments(), DdTrainerInputStartArgs.class);
        ddTrainerInputStart.setId(ddTrainerInputStartArgs.getJobId());
        ddTrainerInputStart.setWorkspaceId(eventInput.getWorkspaceId());

        List<TrainedCrawledUrl> trainedDocuments = crawledTrainingRepository.getAllTrainedDocuments(eventInput.getWorkspaceId());
        List<String> seeds = new LinkedList<>();
        for (TrainedCrawledUrl trainedDocument : trainedDocuments){
            seeds.add(trainedDocument.getUrl());
        }

        DdModelerOutput ddModelerOutput = ddRepository.getPageModel(eventInput.getWorkspaceId());

        ddTrainerInputStart.setSeeds(seeds);
        ddTrainerInputStart.setPage_model(ddModelerOutput.getModel());
        return ddTrainerInputStart;

    }

    public DdTrainerInputStop getDdTrainerInputStop(EventInput eventInput) throws IOException {
        DdTrainerInputStop ddTrainerInputStop = new DdTrainerInputStop();

        JsonMapper<DdTrainerInputStopArgs> jsonMapper= new JsonMapper();
        DdTrainerInputStopArgs ddTrainerInputStopArgs = jsonMapper.toObject(eventInput.getArguments(), DdTrainerInputStopArgs.class);
        ddTrainerInputStop.setId(ddTrainerInputStopArgs.getJobId());

        return ddTrainerInputStop;
    }

    private static class DdTrainerInputStartArgs{

        private String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }
    }

    private static class DdTrainerInputStopArgs{

        private String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }
    }
}
