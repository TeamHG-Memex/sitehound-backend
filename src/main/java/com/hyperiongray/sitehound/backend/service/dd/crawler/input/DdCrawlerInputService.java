package com.hyperiongray.sitehound.backend.service.dd.crawler.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.event.DdCrawlerInputStartArgs;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStartDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerInputStopDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.BroadCrawlRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.KeywordsRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 2/10/16.
 */
@Service
public class DdCrawlerInputService {

    @Autowired private DdRepository ddRepository;
    @Autowired private KeywordsRepository keywordsRepository;
    @Autowired private BroadCrawlRepository broadCrawlRepository;

    private JsonMapper<DdCrawlerInputStartArgs> jsonMapperDdCrawlerInputStartArgs = new JsonMapper();

    public DdCrawlerInputStartDto getDdCrawlerInputStart(EventInput eventInput) throws IOException {

        DdCrawlerInputStartArgs eventInputStartArgs = jsonMapperDdCrawlerInputStartArgs.toObject(eventInput.getArguments(), DdCrawlerInputStartArgs.class);

        DdCrawlerInputStartDto ddCrawlerInputStartDto = new DdCrawlerInputStartDto();
        ddCrawlerInputStartDto.setId(eventInputStartArgs.getJobId());
        ddCrawlerInputStartDto.setWorkspaceId(eventInput.getWorkspaceId());

        ddCrawlerInputStartDto.setBroadness(eventInputStartArgs.getBroadness());
        ddCrawlerInputStartDto.setPageLimit(eventInputStartArgs.getnResults());


        Map<String, String> models = ddRepository.getModels(eventInput.getWorkspaceId());
        ddCrawlerInputStartDto.setPageModel(models.get("page_model"));
        ddCrawlerInputStartDto.setLinkModel(models.get("link_model"));

        List<String> seeds = keywordsRepository.getRelevantTrainedUrls(eventInput.getWorkspaceId());
        ddCrawlerInputStartDto.setSeeds(seeds);


        List<String> hints = broadCrawlRepository.getPinnedUrls(eventInput.getWorkspaceId());
        ddCrawlerInputStartDto.setHints(hints);

        return ddCrawlerInputStartDto;
    }

    public DdCrawlerInputStopDto getDdCrawlerInputStop(String workspaceId){
        DdCrawlerInputStopDto ddCrawlerInputStopDto = new DdCrawlerInputStopDto();
        ddCrawlerInputStopDto.setId(workspaceId);
        return ddCrawlerInputStopDto;
    }

}
