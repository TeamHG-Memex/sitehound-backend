package com.hyperiongray.sitehound.backend.test.kafka.login;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.dd.login.DdLoginInputBrokerService;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.test.kafka.SyncProducer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, KafkaTestConfiguration.class})
public class LoginInputKafkaTemplateTests {

    private static final String TEMPLATE_TOPIC = "dd-login-input";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private DdLoginInputBrokerService ddLoginInputBrokerService;

    @Autowired
    private SyncProducer producer;


    @Test
    public void testTemplate() {

        String input = "{\n" +
                "    \"workspace_id\":\"57ea86a9d11ff300054a3519\"," +
                "    \"job_id\":\"57ea86a9d11ff300054a3519\"," +
                "    \"domain\":\"example.com\"," +
                "    \"url\": \"http://example.com/login\", "+
                "    \"keys\": [\"txtUser\", \"txtPassword\"], " +
                "    \"screenshot\":\"57ea86a9d11ff300054a351.....afazzz9\" "+
                "}";

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, ddLoginInputBrokerService, input);
    }

}