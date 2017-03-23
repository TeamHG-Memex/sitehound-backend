package com.hyperiongray.sitehound.backend.kafka.producer;
//
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
import com.hyperiongray.framework.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by tomas on 2/4/16.
 */
@Service
public class LocalQueueProducer{

	@Autowired
	private KafkaProducer kafkaProducer;

	public void send(String topic, String data){
		kafkaProducer.produce(topic, data);
	}
//
//	private Producer kafkaProducer;
//	private QueueProducer queueProducer;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(LocalQueueProducer.class);
//
//	@Value( "${kafka.metadata.broker.list}" ) private String brokers;
//
//	@PostConstruct
//	public void init(){
//		build();
//	}
//
//	private void build(){
//		Properties props = new Properties();
//		props.put("metadata.broker.list", brokers);
//		props.put("serializer.class", "kafka.serializer.StringEncoder");
//		props.put("org.hyperiongray.googlecrawler.kafka.producer.partitioner.class", "SimplePartitioner");
//		props.put("request.required.acks", "1");
//
//		ProducerConfig producerConfig = new ProducerConfig(props);
//		kafka.javaapi.producer.Producer<String, String> kafkaProducer = new kafka.javaapi.producer.Producer<String, String>(producerConfig);
//		this.kafkaProducer = kafkaProducer;
//		LOGGER.info(props.toString());
//	}
//
////	protected Producer getProducer(){
////		return kafkaProducer;
////	}
//
//	public void send(String inputQueue, String message){
//		KeyedMessage<String, String> data = new KeyedMessage<String, String>(inputQueue, String.valueOf(System.currentTimeMillis()), message);
//		kafkaProducer.send(data);
//
//	}


}
