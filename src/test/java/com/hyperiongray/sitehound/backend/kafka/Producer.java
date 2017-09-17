package com.hyperiongray.sitehound.backend.kafka;

import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaProducerTestUtil kafkaProducerTestUtil;

    public void produce(String topic, KafkaEmbedded embeddedKafka, BrokerService brokerService, String input) {
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
