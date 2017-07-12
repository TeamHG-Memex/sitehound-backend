package com.hyperiongray.framework.kafka.service;

import org.springframework.kafka.support.Acknowledgment;

/**
 * Created by tomas on 22/03/17.
 */
public interface AcknowledgibleActivity {

    void listen(
            String data,
//            Integer key,
            int partition,
            String topic,
            Acknowledgment acknowledgment
            );
}
