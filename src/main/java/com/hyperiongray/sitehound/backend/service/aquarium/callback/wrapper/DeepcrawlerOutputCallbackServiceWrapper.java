package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerAquariumCallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 3/10/16.
 */

public class DeepcrawlerOutputCallbackServiceWrapper extends BaseCallbackServiceWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeepcrawlerOutputCallbackServiceWrapper.class);

    private AquariumInput aquariumInput;
    private final DdDeepcrawlerAquariumCallbackService callbackService;
    private String domain;

    public DeepcrawlerOutputCallbackServiceWrapper(AquariumInput aquariumInput, DdDeepcrawlerAquariumCallbackService callbackService, String domain) {
        this.aquariumInput = aquariumInput;
        this.callbackService = callbackService;
        this.domain = domain;
    }

    @Override
    public void execute(AquariumInternal aquariumInternal){
        callbackService.process(aquariumInput, aquariumInternal, domain);
    }
}
