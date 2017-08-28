package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.DefaultProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 3/10/16.
 */

public class DefaultCallbackServiceWrapper extends BaseCallbackServiceWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCallbackServiceWrapper.class);

    private final AquariumInput aquariumInput;
    private final DefaultProcess baseAquariumCallbackService;

    public DefaultCallbackServiceWrapper(AquariumInput aquariumInput, DefaultProcess baseAquariumCallbackService) {
        this.aquariumInput = aquariumInput;
        this.baseAquariumCallbackService = baseAquariumCallbackService;
    }

    @Override
    public void execute(String url, AquariumInternal aquariumInternal){
        baseAquariumCallbackService.process(aquariumInput, aquariumInternal);
    }
}
