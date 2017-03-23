package com.hyperiongray.framework.kafka.example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tomas on 22/03/17.
 */
public class KafkaConsumerExample {

    @KafkaListener(id = "qux", topicPattern = "myTopic1")
    public void listen(@Payload String foo,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.printf("do something");
    }


//    public class Listener {

//        private final CountDownLatch latch1 = new CountDownLatch(1);
//
//        @KafkaListener(id = "foo", topics = "annotated1")
//        public void listen1(String foo) {
//            this.latch1.countDown();
//        }

//    }
}
