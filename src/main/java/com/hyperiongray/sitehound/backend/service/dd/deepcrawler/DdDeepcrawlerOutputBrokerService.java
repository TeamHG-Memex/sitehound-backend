package com.hyperiongray.sitehound.backend.service.dd.deepcrawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.DdDeepcrawlerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.PageSample;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerOutputAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DeepcrawlerOutputCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.aquarium.clientCallback.AquariumAsyncClientCallback;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.MetadataBuilder;
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
public class DdDeepcrawlerOutputBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerOutputBrokerService.class);

    @Autowired private DdDeepcrawlerOutputAquariumCallbackService ddDeepcrawlerOutputAquariumCallbackService;
    @Autowired private AquariumAsyncClient aquariumClient;
    @Autowired private MetadataBuilder metadataBuilder;
    @Autowired private CrawlJobRepository crawlJobRepository;

    @Override
    public void process(String jsonInput, Semaphore semaphore){

        try{
            LOGGER.debug("DdDeepcrawlerOutputBrokerService consumer Permits:" + semaphore.availablePermits());
            LOGGER.debug("Receiving response size: " + jsonInput.length());
            JsonMapper<DdDeepcrawlerOutput> jsonMapper= new JsonMapper();
            DdDeepcrawlerOutput ddDeepcrawlerOutput = jsonMapper.toObject(jsonInput, DdDeepcrawlerOutput.class);

//            Metadata metadata = metadataBuilder.buildFromDeepcrawlerOutput(ddDeepcrawlerOutput.getId());
//            Metadata metadata = new Metadata();
//            metadata.setCrawlType(Constants.CrawlType.DEEPCRAWL);
//            metadata.setSource("DD");
//            metadata.setStrTimestamp(String.valueOf(System.currentTimeMillis()));
//            metadata.setWorkspace(crawlJobRepository.getWorkspaceId(jobId));
//            metadata.setTimestamp(System.currentTimeMillis());
//            metadata.setCallbackQueue("");
//            metadata.setJobId(jobId);
//            metadata.setCrawlEntityType(Constants.CrawlEntityType.DD);
//            metadata.setnResults(1000);

//            String workspaceId = crawlJobRepository.getWorkspaceId(ddDeepcrawlerOutput.getId());
//            new CrawlJob.Builder()
//                    .withWorkspaceId(workspaceId)
//                    .withJobId(ddDeepcrawlerOutput.getId())
//                    .withCrawlType(Constants.CrawlType.DEEPCRAWL)
//                    .withTimestamp(System.currentTimeMillis())
//                    .withNResultsRequested()

            CrawlJob crawlJob = crawlJobRepository.get(ddDeepcrawlerOutput.getId());

            for (PageSample pageSample : ddDeepcrawlerOutput.getPageSamples()){
//                AquariumInput aquariumInput = new AquariumInput(metadata);
//                aquariumInput.setUrl(pageSample.getUrl());
                DeepcrawlerOutputCallbackServiceWrapper callbackServiceWrapper = new DeepcrawlerOutputCallbackServiceWrapper(crawlJob, ddDeepcrawlerOutputAquariumCallbackService, pageSample.getDomain());
                AquariumAsyncClientCallback aquariumAsyncClientCallback = new AquariumAsyncClientCallback(pageSample.getUrl(), semaphore, callbackServiceWrapper);
                aquariumClient.fetch(pageSample.getUrl(), new ContentResponseHandler(), aquariumAsyncClientCallback);
            }
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
        finally{
            LOGGER.info("DdDeepcrawlerOutputBrokerService Consumer Permits (one will be released now): " + semaphore.availablePermits());
            semaphore.release();
        }
    }
}
