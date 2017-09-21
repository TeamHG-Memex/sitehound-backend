package com.hyperiongray.sitehound.backend.service.dd.trainer.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.TrainerModelRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdTrainerRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdTrainerOutputModelBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerOutputModelBrokerService.class);

    @Autowired private DdTrainerRepository ddTrainerRepository;
    @Autowired private TrainerModelRepository trainerModelRepository;
    @Autowired private CrawlJobRepository crawlJobRepository;

    @Override
    @Deprecated //this is not published anymore by dd. It's used only internally
    public void process(String jsonInput){
        try{
            LOGGER.debug("Receiving response size: " + jsonInput.length());
            JsonMapper<DdTrainerOutputModel> jsonMapper= new JsonMapper();
            DdTrainerOutputModel ddTrainerOutputModel = jsonMapper.toObject(jsonInput, DdTrainerOutputModel.class);
            LOGGER.info("DdTrainerOutputModelBrokerService from ddTrainerOutputModel: " + ddTrainerOutputModel);

            String workspaceId = crawlJobRepository.getWorkspaceId(ddTrainerOutputModel.getId());
            trainerModelRepository.save(workspaceId, ddTrainerOutputModel);
            ddTrainerRepository.saveModelProgress(ddTrainerOutputModel.getId());
            crawlJobRepository.updateJobStatus(ddTrainerOutputModel.getId(), Constants.JobStatus.FINISHED);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }

}
