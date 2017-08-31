package com.hyperiongray.framework.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomas on 22/03/17.
 */
@Configuration
@EnableKafka
public class KafkaConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConfig.class);

    private final static String GROUP_ID = "group-spring-kafka";

//    @Value( "${kafka.zookeeper}" ) private String zooKeeper;
    @Value( "${kafka.broker.list}" ) private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        LOGGER.info("bootstrapServers: " + bootstrapServers);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "90000");
//        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "16000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "200000000");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "90000");
        props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, "3000");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        props.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, "3000");
        return props;

//        props.put("zookeeper.session.timeout.ms", "5000");
//        props.put("zookeeper.sync.time.ms", "1000");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("fetch.message.max.bytes", "20000000");

    }
    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);                              //TURBO!!!!
        factory.getContainerProperties().setPollTimeout(3000);
//        factory.getContainerProperties().setPollTimeout(1000);
//        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.RECORD);
        return factory;
    }


    //PRODUCERS
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 100000000);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 209715200);
        return props;
    }
    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
        return new KafkaTemplate<Integer, String>(producerFactory());
    }

}
