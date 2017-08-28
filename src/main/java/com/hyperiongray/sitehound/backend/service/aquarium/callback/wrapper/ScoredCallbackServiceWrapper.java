package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdCrawlerAquariumCallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 3/10/16.
 */

public class ScoredCallbackServiceWrapper extends BaseCallbackServiceWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoredCallbackServiceWrapper.class);

    private AquariumInput aquariumInput;
    private final DdCrawlerAquariumCallbackService scoredAquariumCallbackService;
    private Double score;

    public ScoredCallbackServiceWrapper(AquariumInput aquariumInput, DdCrawlerAquariumCallbackService scoredAquariumCallbackService, Double score) {
        this.aquariumInput = aquariumInput;
        this.scoredAquariumCallbackService = scoredAquariumCallbackService;
        this.score = score;
    }

    @Override
    public void execute(String url, AquariumInternal aquariumInternal){
        scoredAquariumCallbackService.process(aquariumInput, aquariumInternal, score);
    }
}
