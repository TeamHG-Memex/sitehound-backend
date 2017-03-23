package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerOutput;
import com.hyperiongray.sitehound.backend.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 30/09/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class DdRepositoryTest {


    @Autowired
    private DdRepository ddRepository;

    @Test
    public void getModelTest(){

        DdModelerOutput ddModelerOutput = ddRepository.getPageModel("57e9742c166f1c072dcd33d4");
        System.out.println(ddModelerOutput);

    }

}
