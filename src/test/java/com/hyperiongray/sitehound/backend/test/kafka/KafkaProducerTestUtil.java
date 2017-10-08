package com.hyperiongray.sitehound.backend.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.ClassRule;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tomas on 7/09/17.
 */
@Component
public class KafkaProducerTestUtil {

    public void kafkaProducer(String topic, KafkaEmbedded embeddedKafka, MessageListener<Integer, String> messageListener, String input){
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(topic + "-group", "true", embeddedKafka);
        DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<Integer, String>(consumerProps);
        ContainerProperties containerProperties = new ContainerProperties(topic);
        KafkaMessageListenerContainer<Integer, String> container = new KafkaMessageListenerContainer<>(cf, containerProperties);
        final BlockingQueue<ConsumerRecord<Integer, String>> records = new LinkedBlockingQueue<>();
        container.setupMessageListener(messageListener);
        container.setBeanName("templateTests");
        container.start();
        try {
            ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> senderProps = KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
        ProducerFactory<Integer, String> pf = new DefaultKafkaProducerFactory<Integer, String>(senderProps);
        KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
        template.setDefaultTopic(topic);
        template.sendDefault(input);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
