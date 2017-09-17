package com.hyperiongray.sitehound.backend.test.kafka.login;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.model.DdLoginResult;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdLoginRepository;
import com.hyperiongray.sitehound.backend.service.dd.login.DdLoginResultBrokerService;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.test.kafka.Producer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, KafkaTestConfiguration.class})
public class LoginResultBrokerServiceTest {

    private static final String TEMPLATE_TOPIC = "dd-login-result";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private Producer producer;

    @Autowired
    private DdLoginResultBrokerService ddLoginResultBrokerService;

    @MockBean
    private DdLoginRepository ddLoginRepositoryMock;

    @Test
    public void testTemplate() {

        String id = "59b114a2e4dc96629bb2b2fb";
        String result = "failed";

        String input = "{\n" +
                "    \"id\":\"" + id + "\"," +
                "    \"result\":\"" + result + "\" " +
                "}";

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, ddLoginResultBrokerService, input);

        DdLoginResult ddLoginResult = new DdLoginResult();
        ddLoginResult.setId(id);
        ddLoginResult.setResult(result);

        verify(ddLoginRepositoryMock).updateResult(ddLoginResult);

        ArgumentCaptor<DdLoginResult> argument = ArgumentCaptor.forClass(DdLoginResult.class);
        verify(ddLoginRepositoryMock).updateResult(argument.capture());
        assertEquals(ddLoginResult, argument.getValue());

    }

}