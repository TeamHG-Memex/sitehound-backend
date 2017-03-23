package com.hyperiongray.sitehound.backend.service.dd.modeler.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdModelerOutputBrokerService implements BrokerService {

    @Autowired private DdRepository ddRepository;

    private JsonMapper jsonMapper = new JsonMapper();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerOutputBrokerService.class);


    @Override
    public void process(String jsonInput, Semaphore semaphore){

        try{

            LOGGER.info("DdModelerOutputBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response from events: " + jsonInput);
            JsonMapper<DdModelerOutput> jsonMapper= new JsonMapper();
            DdModelerOutput ddModelerOutput = jsonMapper.toObject(jsonInput, DdModelerOutput.class);
            dispatch(ddModelerOutput);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("DdModelerOutputBrokerService Consumer Permits (one will be released now): " + semaphore.availablePermits());
            semaphore.release();
        }

    }


    public void dispatch(DdModelerOutput ddModelerOutput){
        ddRepository.savePageModel(ddModelerOutput);

    }
}
