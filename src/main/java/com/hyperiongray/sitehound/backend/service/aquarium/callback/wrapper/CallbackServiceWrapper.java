package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;


/**
 * Created by tomas on 3/10/16.
 */
public interface CallbackServiceWrapper {

    void execute(String url, AquariumInternal aquariumInternal);

}
