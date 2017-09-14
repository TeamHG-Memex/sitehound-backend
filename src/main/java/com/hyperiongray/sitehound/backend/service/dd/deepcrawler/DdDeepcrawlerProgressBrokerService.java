package com.hyperiongray.sitehound.backend.service.dd.deepcrawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DdDeepcrawlerProgressDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DomainDto;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.model.DeepcrawlerPageRequest;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdDeepcrawlerRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerOutputPagesAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DeepcrawlerOutputCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.aquarium.clientCallback.AquariumAsyncClientCallback;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.SyncBrokerService;
import org.apache.http.client.fluent.ContentResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdDeepcrawlerProgressBrokerService implements SyncBrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerProgressBrokerService.class);

    @Autowired private CrawlJobRepository crawlJobRepository;
    @Autowired private DdDeepcrawlerRepository ddDeepcrawlerRepository;
    @Autowired private DdDeepcrawlerOutputPagesAquariumCallbackService ddDeepcrawlerOutputPagesAquariumCallbackService;
    @Autowired private AquariumAsyncClient aquariumClient;

    @Override
    public void process(String jsonInput){

        try{
            LOGGER.debug("Receiving response size: " + jsonInput.length());
            JsonMapper<DdDeepcrawlerProgressDto> jsonMapper= new JsonMapper();
            DdDeepcrawlerProgressDto ddDeepcrawlerProgressDto = jsonMapper.toObject(jsonInput, DdDeepcrawlerProgressDto.class);

            CrawlJob crawlJob = crawlJobRepository.get(ddDeepcrawlerProgressDto.getId());

            if(!crawlJob.getHasProgress()){
                for(DomainDto domain :ddDeepcrawlerProgressDto.getProgress().getDomains()){
                    DeepcrawlerPageRequest deepcrawlerPageRequest = new DeepcrawlerPageRequest(domain.getUrl(), domain.getDomain(), true);
                    DeepcrawlerOutputCallbackServiceWrapper callbackServiceWrapper = new DeepcrawlerOutputCallbackServiceWrapper(crawlJob, deepcrawlerPageRequest, ddDeepcrawlerOutputPagesAquariumCallbackService);
//                    AquariumAsyncClientCallback aquariumAsyncClientCallback = new AquariumAsyncClientCallback(domain.getUrl(), semaphore, callbackServiceWrapper);
//                    aquariumClient.fetch(domain.getUrl(), new ContentResponseHandler(), aquariumAsyncClientCallback);
                    aquariumClient.fetch(domain.getUrl(), new ContentResponseHandler(), callbackServiceWrapper);
                }
            }

            LOGGER.debug("DdDeepcrawlerProgressBrokerService received ddDeepcrawlerProgress: " + ddDeepcrawlerProgressDto);
            ddDeepcrawlerRepository.saveProgress(ddDeepcrawlerProgressDto);
            LOGGER.info("DdDeepcrawlerProgressBrokerService saved ddDeepcrawlerProgress: ");
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("Finished");
        }

    }

}
