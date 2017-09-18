package com.hyperiongray.sitehound.backend.kafka.login;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdLoginRepository;
import com.hyperiongray.sitehound.backend.service.dd.login.DdLoginInputBrokerService;
import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.Producer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, KafkaTestConfiguration.class})
public class LoginInputBrokerServiceTest {

    private static final String TEMPLATE_TOPIC = "dd-login-input";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private DdLoginInputBrokerService ddLoginInputBrokerService;

    @Autowired
    private Producer producer;

    @MockBean
    private DdLoginRepository ddLoginRepositoryMock;

    @Test
    public void testTemplate() {

        String workspaceId = "57ea86a9d11ff300054a3519";
        String jobId = "57ea86a9d11ff300054a3519";
        String domain = "example.com";
        String url = "http://example.com/login";
        String keysString = "[\"txtUser\", \"txtPassword\"]";
        String screenShot = "57ea86a9d11ff300054a351.....afazzz9";

        String json = "{\n" +
                "    \"workspace_id\":\"" + workspaceId + "\"," +
                "    \"job_id\":\"" + jobId + "\"," +
                "    \"domain\":\"" + domain + "\"," +
                "    \"url\": \"" + url + "\", " +
                "    \"keys\": " + keysString + ", " +
                "    \"screenshot\":\"" + screenShot + "\" " +
                "}";

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, ddLoginInputBrokerService, json);

        List<String> keys = Lists.newArrayList("txtUser", "txtPassword");

        Map<String, String> keyValues = Maps.newHashMap();
        keyValues.put("txtUser","");
        keyValues.put("txtPassword","");

        DdLoginInput ddLoginInput = new DdLoginInput.Builder()
                .withWorkspaceId(workspaceId)
                .withJobId(jobId)
                .withUrl(url)
                .withDomain(domain)
                .withScreenshot(screenShot)
                .withKeysOrder(keys)
                .withKeyValues(keyValues)
                .build();

        verify(ddLoginRepositoryMock).save(ddLoginInput);

        ArgumentCaptor<DdLoginInput> argument = ArgumentCaptor.forClass(DdLoginInput.class);
        verify(ddLoginRepositoryMock).save(argument.capture());
        assertEquals(ddLoginInput, argument.getValue());

    }

}