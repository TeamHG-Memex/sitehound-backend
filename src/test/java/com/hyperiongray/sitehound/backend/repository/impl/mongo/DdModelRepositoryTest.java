package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdModelerRepository;
import org.junit.Assert;
import org.junit.Ignore;
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

    private static String workspaceId = "59676bc6166f1c7543990bf3";
    private static String modelMockString = "my perfect model";
    private static String qualityMockString = "a big quality";


    @Test
//    @Ignore
    public void savePageModel() throws Exception {
        DdModelerOutput ddModelerOutput = new DdModelerOutput();
        ddModelerOutput.setId(workspaceId);
        ddModelerOutput.setModel(modelMockString);
        ddModelerOutput.setQuality(qualityMockString);
        ddModelerRepository.savePageModel(ddModelerOutput);
    }

    @Test
    @Ignore
    public void getPageModel() throws Exception {
        DdModelerOutput ddModelerOutput = ddModelerRepository.getPageModel(workspaceId);
        Assert.assertEquals(ddModelerOutput.getModel(), modelMockString);
        Assert.assertEquals(ddModelerOutput.getQuality(), qualityMockString);
        System.out.println(ddModelerOutput);
    }

    @Test
    @Ignore
    public void deletePageModel() throws Exception {
        ddModelerRepository.deletePageModel(workspaceId);
    }

    @Test
//    @Ignore
    public void saveProgress() throws Exception {
        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setId(workspaceId);
        ddModelerProgress.setPercentageDone(98.13);
        ddModelerRepository.saveProgress(ddModelerProgress);
    }

    @Test
    @Ignore
    public void getProgress(){
        DdModelerProgress progress = ddModelerRepository.getProgress(workspaceId);
        Assert.assertEquals(progress.getPercentageDone(), Double.valueOf(98.13));
    }



    @Test
    public void pageModelCrudTest() throws Exception {
        ddModelerRepository.deletePageModel(workspaceId);

        DdModelerOutput ddModelerOutput = new DdModelerOutput();
        ddModelerOutput.setId(workspaceId);
        ddModelerOutput.setModel(modelMockString);
        ddModelerOutput.setQuality(qualityMockString);
        ddModelerRepository.savePageModel(ddModelerOutput);


        DdModelerOutput ddModelerOutputStored = ddModelerRepository.getPageModel(workspaceId);
        Assert.assertEquals(modelMockString, ddModelerOutputStored.getModel());
        Assert.assertEquals(qualityMockString, ddModelerOutputStored.getQuality());

        ddModelerRepository.deletePageModel(workspaceId);

        DdModelerOutput ddModelerOutput2 = ddModelerRepository.getPageModel(workspaceId);
        Assert.assertEquals(null, ddModelerOutput2.getModel());
        Assert.assertEquals(null, ddModelerOutput2.getQuality());

        ddModelerRepository.deletePageModel(workspaceId);
    }

    @Test
    public void progressCrudTest() throws Exception {

        ddModelerRepository.deletePageModel(workspaceId);

        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setId(workspaceId);
        ddModelerProgress.setPercentageDone(98.13);
        ddModelerRepository.saveProgress(ddModelerProgress);

        DdModelerProgress progress = ddModelerRepository.getProgress(workspaceId);
        Assert.assertEquals("98.13", progress.getPercentageDone());

        ddModelerRepository.deletePageModel(workspaceId);

        DdModelerProgress progressDeleted = ddModelerRepository.getProgress(workspaceId);
        Assert.assertEquals(progressDeleted.getPercentageDone(), null);

        ddModelerRepository.deletePageModel(workspaceId);
    }


}
