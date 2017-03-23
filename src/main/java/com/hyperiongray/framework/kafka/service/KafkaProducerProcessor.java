package com.hyperiongray.framework.kafka.service;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

import java.io.IOException;

/**
 * Created by tomas on 22/03/17.
 */
public interface KafkaProducerProcessor<T extends KafkaDto> {

    void submit(T kafkaDto) throws IOException;
}
