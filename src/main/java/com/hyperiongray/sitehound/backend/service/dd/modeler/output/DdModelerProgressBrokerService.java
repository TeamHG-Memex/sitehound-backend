package com.hyperiongray.sitehound.backend.service.dd.modeler.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdModelerProgressRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdModelerProgressBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerProgressBrokerService.class);

    @Autowired private DdModelerProgressRepository ddModelerProgressRepository;

    @Override
    public void process(String jsonInput){

        try{
            LOGGER.info("DdModelerProgressBrokerService consumer without permits:");
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdModelerProgress> jsonMapper= new JsonMapper();
            DdModelerProgress ddModelerProgress = jsonMapper.toObject(jsonInput, DdModelerProgress.class);
            ddModelerProgressRepository.saveProgress(ddModelerProgress);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("DdTrainerOutputPagesBrokerService without consumer permits");
        }
    }
}
