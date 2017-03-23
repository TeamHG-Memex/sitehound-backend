package com.hyperiongray.sitehound.backend.service.dd.trainer.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdTrainerOutputModelBrokerService implements BrokerService {

    @Autowired private DdRepository ddRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerOutputModelBrokerService.class);

    @Override
    public void process(String jsonInput, Semaphore semaphore){

        try{

            LOGGER.info("DdTrainerOutputModelBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdTrainerOutputModel> jsonMapper= new JsonMapper();
            DdTrainerOutputModel ddTrainerOutputModel = jsonMapper.toObject(jsonInput, DdTrainerOutputModel.class);
            dispatch(ddTrainerOutputModel);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("DdModelerOutputBrokerService Consumer Permits (one will be released now): " + semaphore.availablePermits());
            semaphore.release();
        }

    }

    public void dispatch(DdTrainerOutputModel ddTrainerOutputModel){
        ddRepository.saveLinkModel(ddTrainerOutputModel);

    }
}
