package com.hyperiongray.sitehound.backend.integration.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.integration.IntegrationTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.TrainerModelRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * Created by tomas on 19/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@ActiveProfiles("integration-test")
@Ignore
public class TrainerModelRepositoryTest {

    @Autowired private TrainerModelRepository trainerModelRepository;

    private String workspaceId = "adba333333312345678";
    private String id = "12345678";
    private String model = "big model page model";

    @Test
    public void crud() throws Exception {
        trainerModelRepository.delete(workspaceId);
        DdTrainerOutputModel ddTrainerOutputModel = new DdTrainerOutputModel();
        ddTrainerOutputModel.setId(id);
        ddTrainerOutputModel.setLink_model(model);
        trainerModelRepository.save(workspaceId, ddTrainerOutputModel);


        Optional<DdTrainerOutputModel> ddTrainerOutputModelOptional = trainerModelRepository.get(workspaceId);

        DdTrainerOutputModel ddTrainerOutputModelSaved = trainerModelRepository.get(workspaceId).get();
        Assert.assertEquals(ddTrainerOutputModel.getId(), ddTrainerOutputModelSaved.getId());
        Assert.assertEquals(ddTrainerOutputModel.getLink_model(), ddTrainerOutputModelSaved.getLink_model());
        System.out.println(ddTrainerOutputModelSaved);

        trainerModelRepository.delete(workspaceId);

        Optional<DdTrainerOutputModel> ddTrainerOutputModelSavedAfterDeleteOptional = trainerModelRepository.get(workspaceId);
        Assert.assertTrue(ddTrainerOutputModelSavedAfterDeleteOptional.isPresent());
    }

}