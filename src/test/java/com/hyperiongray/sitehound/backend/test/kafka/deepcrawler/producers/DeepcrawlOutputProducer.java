package com.hyperiongray.sitehound.backend.test.kafka.deepcrawler.producers;

import com.hyperiongray.sitehound.backend.service.dd.deepcrawler.DdDeepcrawlerOutputBrokerService;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaProducerTestUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

/**
 * Created by tomas on 7/09/17.
 */
@Service
public class DeepcrawlOutputProducer {

    @Autowired
    private DdDeepcrawlerOutputBrokerService ddDeepcrawlerOutputBrokerService;

    @Autowired
    private KafkaProducerTestUtil kafkaProducerTestUtil;

    public void produce(String topic, KafkaEmbedded embeddedKafka, String input) {

        MessageListener<Integer, String> messageListener = new MessageListener<Integer, String>() {
            @Override
            public void onMessage(ConsumerRecord<Integer, String> record) {
                ddDeepcrawlerOutputBrokerService.process(record.value(), new Semaphore(1));
            }
        };

        kafkaProducerTestUtil.kafkaProducer(topic, embeddedKafka, messageListener, input);

    }
}
