package com.hyperiongray.sitehound.backend.service.dd.crawler.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.ScoredProcess;
import org.apache.http.client.fluent.ContentResponseHandler;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutput;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdCrawlerAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.ScoredCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.aquarium.clientCallback.AquariumAsyncClientCallback;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.MetadataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdCrawlerOutputPagesBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdCrawlerOutputPagesBrokerService.class);

    @Autowired private DdCrawlerAquariumCallbackService ddCrawlerAquariumCallbackService;
    @Autowired private AquariumAsyncClient aquariumClient;
    @Autowired private MetadataBuilder metadataBuilder;

    @Override
    public void process(String jsonInput, Semaphore semaphore){
        try{
            LOGGER.info("DdCrawlerOutputPagesBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdCrawlerOutput> jsonMapper= new JsonMapper();
            DdCrawlerOutput ddCrawlerOutput = jsonMapper.toObject(jsonInput, DdCrawlerOutput.class);
            Metadata metadata = metadataBuilder.buildFromCrawlerOutput(ddCrawlerOutput.getId());
            for (PageSample pageSample : ddCrawlerOutput.getPage_sample()){
                AquariumInput aquariumInput = new AquariumInput(metadata);
                aquariumInput.setUrl(pageSample.getUrl());
                ScoredCallbackServiceWrapper scoredCallbackServiceWrapper = new ScoredCallbackServiceWrapper(ddCrawlerAquariumCallbackService, pageSample.getScore());
                AquariumAsyncClientCallback aquariumAsyncClientCallback = new AquariumAsyncClientCallback(aquariumInput, semaphore, scoredCallbackServiceWrapper);
                aquariumClient.fetch(aquariumInput.getUrl(), new ContentResponseHandler(), aquariumAsyncClientCallback);
            }
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }


}
