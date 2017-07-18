package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository.LINK_MODEL_FIELD;
import static com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository.WORKSPACE_COLLECTION_NAME;

/**
 * Created by tomas on 14/07/17.
 */
@Repository
public class DdTrainerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerRepository.class);

    @Autowired private MongoRepository mongoRepository;
    @Autowired private CrawlJobRepository crawlJobRepository;


    public void saveLinkModel(DdTrainerOutputModel ddTrainerOutputModel){
        Document document = new Document();
        document.put("model", ddTrainerOutputModel.getLink_model());
        String workspaceId = crawlJobRepository.getWorkspaceId(ddTrainerOutputModel.getId());
        LOGGER.info("About to saveLinkModel jobId:" + ddTrainerOutputModel.getId() + ", workspaceId: " + workspaceId);
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, workspaceId, LINK_MODEL_FIELD, document);
    }

    public void saveProgress(DdTrainerOutputProgress ddTrainerOutputProgress) {
        String workspaceId = crawlJobRepository.getWorkspaceId(ddTrainerOutputProgress.getId());

        LOGGER.info("About to trainer saveProgress for jobId:" + ddTrainerOutputProgress.getId() +", workspaceId:" + workspaceId);
        Document document = new Document();
        document.put("trainer_progress", ddTrainerOutputProgress.getProgress());
        document.put("percentage_done", ddTrainerOutputProgress.getPercentageDone());

        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, workspaceId, "dd_trainer", document);
    }

}
