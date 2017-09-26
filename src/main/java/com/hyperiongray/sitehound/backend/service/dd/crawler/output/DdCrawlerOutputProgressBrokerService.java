package com.hyperiongray.sitehound.backend.service.dd.crawler.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdCrawlerRepository;
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
public class DdCrawlerOutputProgressBrokerService implements BrokerService {

    @Autowired private CrawlJobRepository crawlJobRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(DdCrawlerOutputProgressBrokerService.class);

    @Override
    public void process(String jsonInput){

        try{
            LOGGER.debug("Receiving response size: " + jsonInput.length());
            JsonMapper<DdCrawlerOutputProgress> jsonMapper= new JsonMapper();
            DdCrawlerOutputProgress ddCrawlerOutputProgress = jsonMapper.toObject(jsonInput, DdCrawlerOutputProgress.class);
            LOGGER.debug("DdCrawlerOutputProgressBrokerService received ddCrawlerOutputProgress: " + ddCrawlerOutputProgress);
            crawlJobRepository.saveProgress(ddCrawlerOutputProgress);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }

}
