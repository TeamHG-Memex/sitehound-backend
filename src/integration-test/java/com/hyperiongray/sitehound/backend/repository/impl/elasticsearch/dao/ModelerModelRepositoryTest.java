package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by tomas on 19/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class ModelerModelRepositoryTest {

    @Autowired
    private ModelerModelRepository modelerModelRepository = new ModelerModelRepository();

    private String id = "12345678";
    private String model = "big modell..d.adf";
    private String quality = "big modell..d.adf";


    @Test
    public void crud() throws Exception {
        modelerModelRepository.delete(id);
        DdModelerOutput ddModelerOutput = new DdModelerOutput();
        ddModelerOutput.setWorkspaceId(id);
        ddModelerOutput.setModel(model);
        ddModelerOutput.setQuality(quality);
        modelerModelRepository.save(ddModelerOutput);

        DdModelerOutput ddModelerOutputSaved = modelerModelRepository.get(id);
        Assert.assertEquals(ddModelerOutput.getWorkspaceId(), ddModelerOutputSaved.getWorkspaceId());
        Assert.assertEquals(ddModelerOutput.getModel(), ddModelerOutputSaved.getModel());
        Assert.assertEquals(ddModelerOutput.getQuality(), ddModelerOutputSaved.getQuality());
        System.out.println(ddModelerOutputSaved);

        modelerModelRepository.delete(id);

        DdModelerOutput ddModelerOutputSavedAfterDelete = modelerModelRepository.get(id);
        Assert.assertNull(ddModelerOutputSavedAfterDelete);
    }


    @Test
    public void getTest() throws IOException {
//        NotFoundError: TransportError(404, u'{"_index":"modeler","_type":"model","_id":"59676bc6166f1c7543990bf3","found":false}')
        DdModelerOutput ddModelerOutputSaved = modelerModelRepository.get(id);
        System.out.println(ddModelerOutputSaved);
    }
}