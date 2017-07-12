package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.translator.dd.DdModelerRepository;
import org.junit.Assert;
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
public class DdModelRepositoryTest {

    @Autowired private DdModelerRepository ddModelerRepository;

    @Test
    public void savePageModel() throws Exception {
    }

    @Test
    public void getPageModel() throws Exception {
        DdModelerOutput ddModelerOutput = ddModelerRepository.getPageModel("57e9742c166f1c072dcd33d4");
        System.out.println(ddModelerOutput);
    }

    @Test
    public void saveProgress() throws Exception {
        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setId("57f665c4166f1c0a748af3ee");
        ddModelerProgress.setPercentageDone("98.13");
        ddModelerRepository.saveProgress(ddModelerProgress);
    }

    @Test
    public void getProgress(){
        String workspaceId = "57f665c4166f1c0a748af3ee";
        DdModelerProgress progress = ddModelerRepository.getProgress(workspaceId);
        Assert.assertEquals(progress.getPercentageDone(), "98.13");
    }

}
