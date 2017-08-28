package com.hyperiongray.sitehound.backend.service.dd.deepcrawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DdDeepcrawlerProgress;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.Domain;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.model.DeepcrawlerPageRequest;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdDeepcrawlerRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerOutputPagesAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DeepcrawlerOutputCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.aquarium.clientCallback.AquariumAsyncClientCallback;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
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
public class DdDeepcrawlerProgressBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerProgressBrokerService.class);

    @Autowired private CrawlJobRepository crawlJobRepository;
    @Autowired private DdDeepcrawlerRepository ddDeepcrawlerRepository;
    @Autowired private DdDeepcrawlerOutputPagesAquariumCallbackService ddDeepcrawlerOutputPagesAquariumCallbackService;
    @Autowired private AquariumAsyncClient aquariumClient;

    @Override
    public void process(String jsonInput, Semaphore semaphore){

        try{
            LOGGER.debug("DdDeepcrawlerProgressBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response size: " + jsonInput.length());
            JsonMapper<DdDeepcrawlerProgress> jsonMapper= new JsonMapper();
            DdDeepcrawlerProgress ddDeepcrawlerProgress = jsonMapper.toObject(jsonInput, DdDeepcrawlerProgress.class);


            CrawlJob crawlJob = crawlJobRepository.get(ddDeepcrawlerProgress.getId());

            if(!crawlJob.getHasProgress()){
                for(Domain domain :ddDeepcrawlerProgress.getProgress().getDomains()){
                    DeepcrawlerPageRequest deepcrawlerPageRequest = new DeepcrawlerPageRequest(domain.getUrl(), domain.getDomain(), true);
                    DeepcrawlerOutputCallbackServiceWrapper callbackServiceWrapper = new DeepcrawlerOutputCallbackServiceWrapper(crawlJob, deepcrawlerPageRequest, ddDeepcrawlerOutputPagesAquariumCallbackService);
                    AquariumAsyncClientCallback aquariumAsyncClientCallback = new AquariumAsyncClientCallback(domain.getUrl(), semaphore, callbackServiceWrapper);
                    aquariumClient.fetch(domain.getUrl(), new ContentResponseHandler(), aquariumAsyncClientCallback);
                }
            }

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
