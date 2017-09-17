package com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdTrainerOutputPagesAquariumCallbackService;

public class DdTrainerOutputPagesCallbackServiceWrapper implements CallbackServiceWrapper {

    private final Metadata metadata;
    private final PageSample pageSample;
    private final DdTrainerOutputPagesAquariumCallbackService callbackService;

    public DdTrainerOutputPagesCallbackServiceWrapper(PageSample pageSample, Metadata metadata, DdTrainerOutputPagesAquariumCallbackService ddTrainerOutputPagesAquariumCallbackService) {
        this.metadata = metadata;
        this.pageSample = pageSample;
        this.callbackService = ddTrainerOutputPagesAquariumCallbackService;
    }

    @Override
    public void execute(String url, AquariumInternal aquariumInternal) {
        callbackService.process(pageSample, metadata, aquariumInternal);
    }
}
