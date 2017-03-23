package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;


/**
 * Created by tomas on 3/10/16.
 */
public abstract class BaseCallbackServiceWrapper {

    public abstract void execute(AquariumInput aquariumInput, AquariumInternal aquariumInternal);

}
