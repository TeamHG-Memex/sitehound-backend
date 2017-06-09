package com.hyperiongray.sitehound.backend.service.dd.crawler.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.event.DdCrawlerInputStartArgs;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStop;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawledTrainingRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
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

    private JsonMapper<DdCrawlerInputStartArgs> jsonMapperDdCrawlerInputStartArgs = new JsonMapper();

    public DdCrawlerInputStart getDdCrawlerInputStart(EventInput eventInput) throws IOException {

        DdCrawlerInputStart ddCrawlerInputStart = new DdCrawlerInputStart();

        Map<String, String> models = ddRepository.getModels(eventInput.getWorkspaceId());
        ddCrawlerInputStart.setId(eventInput.getWorkspaceId());
        ddCrawlerInputStart.setPageModel(models.get("page_model"));
        ddCrawlerInputStart.setLinkModel(models.get("link_model"));

        List<String> seeds = crawledTrainingRepository.getRelevantTrainedUrls(eventInput.getWorkspaceId());
        ddCrawlerInputStart.setSeeds(seeds);

        DdCrawlerInputStartArgs eventInputStartArgs = jsonMapperDdCrawlerInputStartArgs.toObject(eventInput.getArguments(), DdCrawlerInputStartArgs.class);
        ddCrawlerInputStart.setBroadness(eventInputStartArgs.getBroadness());
        ddCrawlerInputStart.setPageLimit(eventInputStartArgs.getnResults());

        List<String> hints = crawledTrainingRepository.getPinnedUrls(eventInput.getWorkspaceId());
        ddCrawlerInputStart.setHints(hints);

        return ddCrawlerInputStart;
    }

    public DdCrawlerInputStop getDdCrawlerInputStop(String workspaceId){
        DdCrawlerInputStop ddCrawlerInputStop = new DdCrawlerInputStop();
        ddCrawlerInputStop.setId(workspaceId);
        return ddCrawlerInputStop;
    }

}
