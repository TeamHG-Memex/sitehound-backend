package com.hyperiongray.framework.kafka;

import com.hyperiongray.framework.kafka.service.KafkaProducer;
import com.hyperiongray.sitehound.backend.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by tomas on 23/03/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class KafkaProducerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerTest.class);

    @Autowired private KafkaProducer kafkaProducer;

    @Test
    public void produce() throws Exception {
        kafkaProducer.produce("topic1", "lalala");
    }

    @Test
    public void produceLongData() throws Exception {
        int i=0;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        while(i<1000*1000){
            sb.append(random.nextLong());
            i+=1;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ListenableFutureCallback<SendResult<Integer, String>> listenableFutureCallback = new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                LOGGER.info("finished ok!");
                countDownLatch.countDown();
            }
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Producer failed. Error", ex);
                countDownLatch.countDown();
                throw new RuntimeException("KAFKA producer failed", ex);
//        ...
            }
        };

        kafkaProducer.produce("topic1", sb.toString(), listenableFutureCallback);

        try {
            countDownLatch.await(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void produce1() throws Exception {
    }

}