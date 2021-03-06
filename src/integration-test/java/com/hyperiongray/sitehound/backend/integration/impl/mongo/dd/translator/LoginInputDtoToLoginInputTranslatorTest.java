package com.hyperiongray.sitehound.backend.integration.impl.mongo.dd.translator;

import com.google.common.collect.Lists;
import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator.LoginInputDtoToLoginInputTranslator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * Created by tomas on 22/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class LoginInputDtoToLoginInputTranslatorTest {

    @Autowired private LoginInputDtoToLoginInputTranslator loginInputDtoToLoginInputTranslator;

    @Test
    public void translate() throws Exception {
        String workspaceId = "57ea86a9d11ff300054a3519";
        String jobId = "jobId1223124";
        String url = "http://www.example.com/login";
        ArrayList<String> keys = Lists.newArrayList("user", "password");
        String screenshot = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";


        DdLoginInputDto ddLoginInputDto = new DdLoginInputDto();
        ddLoginInputDto.setJobId(jobId);
        ddLoginInputDto.setKeys(keys);
        ddLoginInputDto.setScreenshot(screenshot);
        ddLoginInputDto.setUrl(url);
        ddLoginInputDto.setWorkspaceId(workspaceId);
        DdLoginInput ddLoginInput = loginInputDtoToLoginInputTranslator.translate(ddLoginInputDto);
        Assert.assertEquals(ddLoginInput.getWorkspaceId(), workspaceId);
        Assert.assertEquals(ddLoginInput.getJobId(), jobId);
        Assert.assertEquals(ddLoginInput.getUrl(), url);
        Assert.assertEquals("", ddLoginInput.getKeyValues().get("user"));
        Assert.assertEquals("", ddLoginInput.getKeyValues().get("password"));
        Assert.assertEquals(ddLoginInput.getScreenshot(), screenshot);

    }

}