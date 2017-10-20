package com.hyperiongray.sitehound.backend.service.dd.trainer.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputPages;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdTrainerOutputPagesAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DdTrainerOutputPagesCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.MetadataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdTrainerOutputPagesBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerOutputPagesBrokerService.class);

    @Autowired private MetadataBuilder metadataBuilder;
    @Autowired private AquariumAsyncClient aquariumClient;
    @Autowired private DdTrainerOutputPagesAquariumCallbackService ddTrainerOutputPagesAquariumCallbackService;

    @Override
    public void process(String jsonInput){

        try{
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdTrainerOutputPages> jsonMapper= new JsonMapper();
            DdTrainerOutputPages ddTrainerOutputPages = jsonMapper.toObject(jsonInput, DdTrainerOutputPages.class);
            Assert.hasText(ddTrainerOutputPages.getWorkspaceId());
            Assert.notEmpty(ddTrainerOutputPages.getPageSamples());
            Metadata metadata = metadataBuilder.buildFromTrainerOutputPages(ddTrainerOutputPages.getWorkspaceId());

            for (PageSample pageSample : ddTrainerOutputPages.getPageSamples()){
                DdTrainerOutputPagesCallbackServiceWrapper callbackServiceWrapper = new DdTrainerOutputPagesCallbackServiceWrapper(pageSample, metadata, ddTrainerOutputPagesAquariumCallbackService);
                aquariumClient.fetch(pageSample.getUrl(), callbackServiceWrapper);
            }
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }
}
