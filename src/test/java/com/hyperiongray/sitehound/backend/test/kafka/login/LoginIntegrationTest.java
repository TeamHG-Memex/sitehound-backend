package com.hyperiongray.sitehound.backend.test.kafka.login;

import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginResultDto;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.model.DdLoginResult;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.DdLoginRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.test.kafka.login.producers.LoginInputProducer;
import com.hyperiongray.sitehound.backend.test.kafka.login.producers.LoginResultProducer;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 7/09/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, KafkaTestConfiguration.class})
public class LoginIntegrationTest {

    private static final String LOGIN_INPUT_TOPIC = "dd-login-input";
    private static final String LOGIN_RESULT_TOPIC = "dd-login-result";

    @ClassRule public static KafkaEmbedded loginInputEmbeddedKafka = new KafkaEmbedded(1, true, LOGIN_INPUT_TOPIC);
    @ClassRule public static KafkaEmbedded loginResultEmbeddedKafka = new KafkaEmbedded(1, true, LOGIN_RESULT_TOPIC);

    @Autowired private LoginInputProducer loginInputProducer;
    @Autowired private LoginResultProducer loginResultProducer;

    @Autowired private DdLoginRepository ddLoginRepository;

    @Test
    public void testTemplate() {

        String workspaceId = "aaaaaaaaaaaaaaaaaaaaaaaaa";
        String jobId = "bbbbbbbbbbbbbbbbbbbbbbb";
        String domain = "test-example-" + System.currentTimeMillis() + ".com";
        String url = "http://test-example.com/login";
        String screenshot = "test-example-snapshot57ea86a9d11ff300054a351.....afazzz9";
        String result = "success";
        List<String> keys = Lists.newArrayList("txtUser", "txtPassword");
        Map<String, String> keyValues = Maps.newHashMap("txtUser", "");
        keyValues.put("txtPassword", "");

        DdLoginInputDto ddLoginInputDto = new DdLoginInputDto();
        ddLoginInputDto.setWorkspaceId(workspaceId);
        ddLoginInputDto.setJobId(jobId);
        ddLoginInputDto.setUrl(url);
        ddLoginInputDto.setScreenshot(screenshot);
        ddLoginInputDto.setDomain(domain);
        ddLoginInputDto.setKeys(keys);

        JsonMapper<DdLoginInputDto> loginInputJsonMapper = new JsonMapper();
        String loginInput="";
        try {
            loginInput = loginInputJsonMapper.toString(ddLoginInputDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginInputProducer.produce(LOGIN_INPUT_TOPIC, loginInputEmbeddedKafka, loginInput);

        DdLoginInput ddLoginInputSaved = ddLoginRepository.getByDomain(domain);
        Assert.assertEquals(workspaceId, ddLoginInputSaved.getWorkspaceId());
        Assert.assertEquals(jobId, ddLoginInputSaved.getJobId());
        Assert.assertEquals(domain, ddLoginInputSaved.getDomain());
        Assert.assertEquals(url, ddLoginInputSaved.getUrl());
        Assert.assertEquals(keys, ddLoginInputSaved.getKeysOrder());
//        Assert.assertEquals(screenshot, ddLoginInputSaved.getScreenshot());

        DdLoginResultDto ddLoginResultDto = new DdLoginResultDto();
        ddLoginResultDto.setId(ddLoginInputSaved.getId());
        ddLoginResultDto.setResult(result);

        JsonMapper<DdLoginResultDto> loginResultJsonMapper = new JsonMapper();
        String loginResult = "";
        try {
            loginResult = loginResultJsonMapper.toString(ddLoginResultDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginResultProducer.produce(LOGIN_RESULT_TOPIC, loginResultEmbeddedKafka, loginResult);

        DdLoginInput ddLoginResultSaved = ddLoginRepository.getByDomain(domain);
        Assert.assertEquals(workspaceId, ddLoginResultSaved.getWorkspaceId());
        Assert.assertEquals(jobId, ddLoginResultSaved.getJobId());
        Assert.assertEquals(domain, ddLoginResultSaved.getDomain());
        Assert.assertEquals(url, ddLoginResultSaved.getUrl());
        Assert.assertEquals(keys, ddLoginResultSaved.getKeysOrder());
        Assert.assertEquals(result, ddLoginResultSaved.getResult());
    }
}
