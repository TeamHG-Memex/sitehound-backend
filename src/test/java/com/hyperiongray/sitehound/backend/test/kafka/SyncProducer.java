package com.hyperiongray.sitehound.backend.test.kafka;

import com.hyperiongray.sitehound.backend.service.crawler.SyncBrokerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.stereotype.Service;

@Service
public class SyncProducer {

    @Autowired
    private KafkaProducerTestUtil kafkaProducerTestUtil;

    public void produce(String topic, KafkaEmbedded embeddedKafka, SyncBrokerService brokerService, String input) {
        MessageListener<Integer, String> messageListener = new MessageListener<Integer, String>() {
            @Override
            public void onMessage(ConsumerRecord<Integer, String> record) {
                System.out.println(record);
                brokerService.process(record.value());
            }
        };

        kafkaProducerTestUtil.kafkaProducer(topic, embeddedKafka, messageListener, input);
    }
}