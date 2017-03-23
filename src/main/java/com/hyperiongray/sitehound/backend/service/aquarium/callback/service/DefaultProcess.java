package com.hyperiongray.sitehound.backend.service.aquarium.callback.service;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;

/**
 * Created by tomas on 3/10/16.
 */
public interface DefaultProcess {

    void process(AquariumInput aquariumInput, AquariumInternal aquariumInternal);

}
