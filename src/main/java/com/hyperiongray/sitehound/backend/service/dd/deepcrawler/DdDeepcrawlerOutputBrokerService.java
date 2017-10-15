package com.hyperiongray.sitehound.backend.service.dd.deepcrawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.DdDeepcrawlerOutputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.PageSampleDto;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.model.DeepcrawlerPageRequest;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerOutputPagesAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DeepcrawlerOutputCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdDeepcrawlerOutputBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerOutputBrokerService.class);

    @Autowired private DdDeepcrawlerOutputPagesAquariumCallbackService ddDeepcrawlerOutputPagesAquariumCallbackService;
    @Autowired private AquariumAsyncClient aquariumClient;
    @Autowired private CrawlJobRepository crawlJobRepository;

    @Override
    public void process(String jsonInput){
        LOGGER.debug("Receiving response size: " + jsonInput.length());
        try{
            JsonMapper<DdDeepcrawlerOutputDto> jsonMapper= new JsonMapper();
            DdDeepcrawlerOutputDto ddDeepcrawlerOutputDto = jsonMapper.toObject(jsonInput, DdDeepcrawlerOutputDto.class);
            Optional<CrawlJob> crawlJobOptional = crawlJobRepository.get(ddDeepcrawlerOutputDto.getId());
            if(crawlJobOptional.isPresent()){
                CrawlJob crawlJob = crawlJobOptional.get();
                for (PageSampleDto pageSample : ddDeepcrawlerOutputDto.getPageSamples()){
                    DeepcrawlerPageRequest deepcrawlerPageRequest = new DeepcrawlerPageRequest(pageSample.getUrl(), pageSample.getDomain(), false);
                    DeepcrawlerOutputCallbackServiceWrapper callbackServiceWrapper = new DeepcrawlerOutputCallbackServiceWrapper(crawlJob, deepcrawlerPageRequest, ddDeepcrawlerOutputPagesAquariumCallbackService);
                    aquariumClient.fetch(pageSample.getUrl(), callbackServiceWrapper);
                }
            }
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }
}
