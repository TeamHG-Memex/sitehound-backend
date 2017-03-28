package com.hyperiongray.framework.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by tomas on 22/03/17.
 */
@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired private KafkaTemplate<Integer, String> kafkaTemplate;

    public void produce(String topic, String data){
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic,data);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                LOGGER.info("producer finished ok!");
            }
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Producer failed. Error", ex);
                throw new RuntimeException("KAFKA producer failed", ex);
            }
        });
    }

    public void produce(String topic, String data, ListenableFutureCallback<SendResult<Integer, String>> listenableFutureCallback){
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic,data);
        future.addCallback(listenableFutureCallback);
    }



}
