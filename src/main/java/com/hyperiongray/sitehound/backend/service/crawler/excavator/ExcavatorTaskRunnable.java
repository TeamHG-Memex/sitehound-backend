package com.hyperiongray.sitehound.backend.service.crawler.excavator;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcavatorTaskRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcavatorTaskRunnable.class);

    private SubscriberInput subscriberInput;
    private ExcavatorBrokerService excavatorBrokerService;

    public ExcavatorTaskRunnable(ExcavatorBrokerService excavatorBrokerService, SubscriberInput subscriberInput){
        this.subscriberInput = subscriberInput;
        this.excavatorBrokerService = excavatorBrokerService;
    }

    @Override
    public void run() {
        LOGGER.info("scheduling: " + subscriberInput);
        try {
            excavatorBrokerService.process(subscriberInput);
        } catch (Exception e) {
            LOGGER.error("Failed to run excavatorBrokerService");

        }
    }
}
