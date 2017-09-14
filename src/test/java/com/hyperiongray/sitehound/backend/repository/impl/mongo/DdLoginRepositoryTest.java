package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.model.DdLoginInputTestHelper;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdLoginRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 22/06/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class DdLoginRepositoryTest {

    @Autowired private DdLoginRepository ddLoginRepository;

    @Test
    public void save() throws Exception {
        DdLoginInput ddLoginInput = DdLoginInputTestHelper.getOne();
        ddLoginRepository.save(ddLoginInput);
    }

}