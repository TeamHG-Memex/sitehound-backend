package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdModelerProgressRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 30/09/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("integration-test")
public class DdModelRepositoryTest {

    @Autowired private DdModelerProgressRepository ddModelerProgressRepository;

    private static String workspaceId = "59c27d45e91b2e11edbaf859";
    private double percentageDone = 98.13;

    @Test
//    @Ignore
    public void saveProgress() throws Exception {
        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setWorkspaceId(workspaceId);
        ddModelerProgress.setPercentageDone(98.13);
        ddModelerProgressRepository.saveProgress(ddModelerProgress);
    }

    @Test
    @Ignore
    public void getProgress(){
        DdModelerProgress progress = ddModelerProgressRepository.getProgress(workspaceId);
        Assert.assertEquals(progress.getPercentageDone(), Double.valueOf(98.13));
    }


    @Test
    public void progressCrudTest() throws Exception {

        ddModelerProgressRepository.deleteProgress(workspaceId);

        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setWorkspaceId(workspaceId);
        ddModelerProgress.setPercentageDone(percentageDone);
        ddModelerProgressRepository.saveProgress(ddModelerProgress);

        DdModelerProgress progress = ddModelerProgressRepository.getProgress(workspaceId);
        Assert.assertEquals(percentageDone, progress.getPercentageDone().doubleValue(), 0.001);

        ddModelerProgressRepository.deleteProgress(workspaceId);

        DdModelerProgress progressDeleted = ddModelerProgressRepository.getProgress(workspaceId);
        Assert.assertEquals(progressDeleted.getPercentageDone().doubleValue(), 0.0, 0.001);

    }


}
