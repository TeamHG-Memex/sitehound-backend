package com.hyperiongray.sitehound.backend.kafka;

import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.service.dd.deepcrawler.DdDeepcrawlerProgressBrokerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.kafka.test.hamcrest.KafkaMatchers.*;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class DeepcrawlerProgressKafkaTemplateTests {

    private static final String TEMPLATE_TOPIC = "dd-deepcrawler-progress";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private DdDeepcrawlerProgressBrokerService ddDeepcrawlerProgressBrokerService;

    @Test
    public void testTemplate() throws Exception {

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(TEMPLATE_TOPIC + "-group", "true", embeddedKafka);
        DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<Integer, String>(consumerProps);
        ContainerProperties containerProperties = new ContainerProperties(TEMPLATE_TOPIC);
        KafkaMessageListenerContainer<Integer, String> container = new KafkaMessageListenerContainer<>(cf, containerProperties);
        final BlockingQueue<ConsumerRecord<Integer, String>> records = new LinkedBlockingQueue<>();
        container.setupMessageListener(new MessageListener<Integer, String>() {

            @Override
            public void onMessage(ConsumerRecord<Integer, String> record) {
                System.out.println(record);
//                records.add(record);
                ddDeepcrawlerProgressBrokerService.process(record.value(), new Semaphore(1));
            }

        });
        container.setBeanName("templateTests");
        container.start();
        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());

        String input = "{" +
                "\"id\": \"59a18279166f1c48c18760c5\"," +
                "\"progress\": {" +
                "\"status\": \"running\"," +
                "\"pages_fetched\": 1468," +
                "\"rpm\": 24000," +
                "\"domains\": [" +
                " {\"url\":\"http://example.com\", \"domain\": \"example.com\", \"pages_fetched\": 1234, \"finished\": false, \"rpm\":12000}," +
                " {\"url\":\"http://google.com\", \"domain\": \"google.com\", \"pages_fetched\": 234, \"finished\": true, \"rpm\":12000}" +
                "]"+
                "}" +
                "}";



        Map<String, Object> senderProps = KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
        ProducerFactory<Integer, String> pf = new DefaultKafkaProducerFactory<Integer, String>(senderProps);
        KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
        template.setDefaultTopic(TEMPLATE_TOPIC);
        template.sendDefault(input);
        Thread.sleep(100000L);
//        assertThat(records.poll(10, TimeUnit.SECONDS), hasValue(null));
    /*
        template.sendDefault(0, 2, "bar");
        ConsumerRecord<Integer, String> received = records.poll(10, TimeUnit.SECONDS);
        assertThat(received, hasKey(2));
        assertThat(received, hasPartition(0));
        assertThat(received, hasValue("bar"));
        template.send(TEMPLATE_TOPIC, 0, 2, "baz");
        received = records.poll(10, TimeUnit.SECONDS);
        assertThat(received, hasKey(2));
        assertThat(received, hasPartition(0));
        assertThat(received, hasValue("baz"));
    */
    }

}