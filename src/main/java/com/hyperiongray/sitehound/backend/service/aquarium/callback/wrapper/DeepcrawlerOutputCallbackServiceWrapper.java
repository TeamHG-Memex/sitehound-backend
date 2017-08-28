package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.model.DeepcrawlerPageRequest;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerOutputAquariumCallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 3/10/16.
 */

public class DeepcrawlerOutputCallbackServiceWrapper extends BaseCallbackServiceWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeepcrawlerOutputCallbackServiceWrapper.class);

    private CrawlJob crawlJob;
    private DeepcrawlerPageRequest deepcrawlerPageRequest;
    private final DdDeepcrawlerOutputAquariumCallbackService callbackService;

    public DeepcrawlerOutputCallbackServiceWrapper(CrawlJob crawlJob, DeepcrawlerPageRequest deepcrawlerPageRequest, DdDeepcrawlerOutputAquariumCallbackService callbackService) {
        this.crawlJob = crawlJob;
        this.deepcrawlerPageRequest = deepcrawlerPageRequest;
        this.callbackService = callbackService;
    }

    @Override
    public void execute(String url, AquariumInternal aquariumInternal){
        callbackService.process(crawlJob, deepcrawlerPageRequest, aquariumInternal);
    }
}
