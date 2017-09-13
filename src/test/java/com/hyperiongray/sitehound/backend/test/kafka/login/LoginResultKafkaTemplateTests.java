package com.hyperiongray.sitehound.backend.test.kafka.login;

import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.test.kafka.login.producers.LoginResultProducer;
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
public class LoginResultKafkaTemplateTests {

    private static final String TEMPLATE_TOPIC = "dd-login-result";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private LoginResultProducer loginResultProducer;

    @Test
    public void testTemplate() {

        String input = "{\n" +
                "    \"id\":\"59b114a2e4dc96629bb2b2fb\"," +
                "    \"result\":\"failed\" "+
                "}";

        loginResultProducer.produce(TEMPLATE_TOPIC, embeddedKafka, input);
    }

}