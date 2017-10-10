package com.hyperiongray.sitehound.backend.integration.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.integration.IntegrationTestConfiguration;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.test.model.DdLoginInputTestHelper;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdLoginRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 22/06/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@ActiveProfiles("integration-test")
public class DdLoginRepositoryTest {

    @Autowired private DdLoginRepository ddLoginRepository;

    @Test
    public void save() throws Exception {
        DdLoginInput ddLoginInput = DdLoginInputTestHelper.getOne();
        ddLoginRepository.save(ddLoginInput);
    }

}