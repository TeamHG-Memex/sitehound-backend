package com.hyperiongray.sitehound.backend.service.dd.deepcrawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DdDeepcrawlerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdDeepcrawlerRepository;
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
public class DdDeepcrawlerProgressBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerProgressBrokerService.class);

    @Autowired private DdDeepcrawlerRepository ddDeepcrawlerRepository;

    @Override
    public void process(String jsonInput, Semaphore semaphore){

        try{
            LOGGER.debug("DdDeepcrawlerProgressBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response size: " + jsonInput.length());
            JsonMapper<DdDeepcrawlerProgress> jsonMapper= new JsonMapper();
            DdDeepcrawlerProgress ddDeepcrawlerProgress = jsonMapper.toObject(jsonInput, DdDeepcrawlerProgress.class);
            LOGGER.debug("DdDeepcrawlerProgressBrokerService received ddDeepcrawlerProgress: " + ddDeepcrawlerProgress);
            ddDeepcrawlerRepository.saveProgress(ddDeepcrawlerProgress);
            LOGGER.info("DdDeepcrawlerProgressBrokerService saved ddDeepcrawlerProgress: ");
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("DdDeepcrawlerProgressBrokerService Consumer Permits (one will be released now): " + semaphore.availablePermits());
            semaphore.release();
        }

    }

}
