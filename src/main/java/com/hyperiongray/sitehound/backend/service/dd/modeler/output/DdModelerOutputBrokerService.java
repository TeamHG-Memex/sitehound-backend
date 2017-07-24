package com.hyperiongray.sitehound.backend.service.dd.modeler.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ModelerModelRepository;
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
public class DdModelerOutputBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerOutputBrokerService.class);

    @Autowired private ModelerModelRepository modelerModelRepository;

    @Override
    public void process(String jsonInput, Semaphore semaphore){

        try{
            LOGGER.info("DdModelerOutputBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response from DdModelerOutputBrokerService size: " + jsonInput.length());
            JsonMapper<DdModelerOutput> jsonMapper= new JsonMapper();
            DdModelerOutput ddModelerOutput = jsonMapper.toObject(jsonInput, DdModelerOutput.class);
            LOGGER.info("Receiving response from DdModelerOutputBrokerService ddModelerOutput: " + ddModelerOutput);
            modelerModelRepository.save(ddModelerOutput);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("DdModelerOutputBrokerService Consumer Permits (one will be released now): " + semaphore.availablePermits());
            semaphore.release();
        }

    }


}
