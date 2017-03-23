package com.hyperiongray.sitehound.backend.service.dd.trainer.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputPages;
import com.hyperiongray.sitehound.backend.kafka.producer.AquariumProducer;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.MetadataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdTrainerOutputPagesBrokerService implements BrokerService {

    @Autowired private DdRepository ddRepository;

    @Autowired private AquariumProducer producer;

    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerOutputPagesBrokerService.class);

    @Override
    public void process(String jsonInput, Semaphore semaphore){

        try{
            LOGGER.info("DdTrainerOutputPagesBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdTrainerOutputPages> jsonMapper= new JsonMapper();
            DdTrainerOutputPages ddTrainerOutputPages = jsonMapper.toObject(jsonInput, DdTrainerOutputPages.class);
            dispatch(ddTrainerOutputPages);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("DdTrainerOutputPagesBrokerService Consumer Permits (one will be released now): " + semaphore.availablePermits());
            semaphore.release();
        }
    }

    public void dispatch(DdTrainerOutputPages ddTrainerOutputPages) throws IOException {
        Metadata metadata = MetadataBuilder.buildFromTrainerOutputPages(ddTrainerOutputPages.getId());
        for (PageSample pageSample : ddTrainerOutputPages.getPage_sample()){
            AquariumInput aquariumInput = new AquariumInput(metadata);
            aquariumInput.setUrl(pageSample.getUrl());
            aquariumInput.setIndex(100);
            producer.submit(aquariumInput);
        }
    }


}
