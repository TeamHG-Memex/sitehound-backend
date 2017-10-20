package com.hyperiongray.sitehound.backend.service.dd.trainer.output;

import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdTrainerRepository;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputProgress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdTrainerOutputProgressBrokerService implements BrokerService {

    @Autowired private DdTrainerRepository ddTrainerRepository;
    @Autowired private CrawlJobRepository crawlJobRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerOutputProgressBrokerService.class);

    @Override
    public void process(String jsonInput){

        try{
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdTrainerOutputProgress> jsonMapper= new JsonMapper();
            DdTrainerOutputProgress ddTrainerOutputProgress = jsonMapper.toObject(jsonInput, DdTrainerOutputProgress.class);
            Assert.hasText(ddTrainerOutputProgress.getWorkspaceId());
            Assert.isNull(ddTrainerOutputProgress.getProgress());

            ddTrainerRepository.saveProgress(ddTrainerOutputProgress);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }
}
