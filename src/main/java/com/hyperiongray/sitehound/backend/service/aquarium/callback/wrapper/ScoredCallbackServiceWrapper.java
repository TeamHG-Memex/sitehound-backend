package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.ScoredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 3/10/16.
 */

public class ScoredCallbackServiceWrapper extends BaseCallbackServiceWrapper {

    private final ScoredProcess scoredAquariumCallbackService;
    private Double score;

    public ScoredCallbackServiceWrapper(ScoredProcess scoredAquariumCallbackService, Double score) {
        this.scoredAquariumCallbackService = scoredAquariumCallbackService;
        this.score = score;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoredCallbackServiceWrapper.class);


    @Override
    public void execute(AquariumInput aquariumInput, AquariumInternal aquariumInternal){
        scoredAquariumCallbackService.process(aquariumInput, aquariumInternal, score);
    }
}
