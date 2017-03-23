package com.hyperiongray.sitehound.backend.service.dd.crawler.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStop;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawledTrainingRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 2/10/16.
 */
@Service
public class DdCrawlerInputService {

    @Autowired private DdRepository ddRepository;
    @Autowired private CrawledTrainingRepository crawledTrainingRepository;


    public DdCrawlerInputStart getDdCrawlerInputStart(String workspaceId) throws IOException {

        DdCrawlerInputStart ddCrawlerInputStart = new DdCrawlerInputStart();

        Map<String, String> models = ddRepository.getModels(workspaceId);

        List<TrainedCrawledUrl> trainedDocuments = crawledTrainingRepository.getTrainedDocuments(workspaceId);
        List<String> seeds = new LinkedList<>();
        for (TrainedCrawledUrl trainedDocument : trainedDocuments){
            seeds.add(trainedDocument.getUrl());
        }

        ddCrawlerInputStart.setId(workspaceId);
        ddCrawlerInputStart.setPage_model(models.get("page_model"));
        ddCrawlerInputStart.setLink_model(models.get("link_model"));
        ddCrawlerInputStart.setSeeds(seeds);

        return ddCrawlerInputStart;
    }

    public DdCrawlerInputStop getDdCrawlerInputStop(String workspaceId){
        DdCrawlerInputStop ddCrawlerInputStop = new DdCrawlerInputStop();
        ddCrawlerInputStop.setId(workspaceId);
        return ddCrawlerInputStop;
    }
}
