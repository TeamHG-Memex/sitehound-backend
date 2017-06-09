package com.hyperiongray.sitehound.backend.service.dd.trainer.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStop;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawledTrainingRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
@Service
public class DdTrainerInputService {


    @Autowired private CrawledTrainingRepository crawledTrainingRepository;
    @Autowired private DdRepository ddRepository;

    public DdTrainerInputStart getDdTrainerInputStart(String workspaceId) {

        DdTrainerInputStart ddTrainerInputStart = new DdTrainerInputStart();

        List<TrainedCrawledUrl> trainedDocuments = crawledTrainingRepository.getAllTrainedDocuments(workspaceId);
        List<String> seeds = new LinkedList<>();
        for (TrainedCrawledUrl trainedDocument : trainedDocuments){
            seeds.add(trainedDocument.getUrl());
        }

        DdModelerOutput ddModelerOutput = ddRepository.getPageModel(workspaceId);

        ddTrainerInputStart.setId(workspaceId);
        ddTrainerInputStart.setSeeds(seeds);
        ddTrainerInputStart.setPage_model(ddModelerOutput.getModel());
        return ddTrainerInputStart;

    }

    public DdTrainerInputStop getDdTrainerInputStop(String workspaceId) {
        DdTrainerInputStop ddTrainerInputStop = new DdTrainerInputStop();
        ddTrainerInputStop.setId(workspaceId);
        return ddTrainerInputStop;

    }
}
