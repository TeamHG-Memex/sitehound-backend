package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.WORKSPACE_COLLECTION_NAME;
import static com.mongodb.client.model.Updates.combine;

/**
 * Created by tomas on 14/07/17.
 */
@Repository
public class DdTrainerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerRepository.class);

    @Autowired private MongoRepository mongoRepository;
    @Autowired private CrawlJobRepository crawlJobRepository;

//    @Deprecated
//    public void saveLinkModel(DdTrainerOutputModel ddTrainerOutputModel){
//        Document document = new Document();
//        document.put("model", ddTrainerOutputModel.getLink_model());
//        String workspaceId = crawlJobRepository.getWorkspaceId(ddTrainerOutputModel.getId());
//        LOGGER.info("About to saveLinkModel jobId:" + ddTrainerOutputModel.getId() + ", workspaceId: " + workspaceId);
//        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, workspaceId, LINK_MODEL_FIELD, document);
//    }


    public void saveProgress(DdTrainerOutputProgress ddTrainerOutputProgress) {
        String workspaceId = crawlJobRepository.getWorkspaceId(ddTrainerOutputProgress.getId());

        LOGGER.info("About to trainer saveProgress for jobId:" + ddTrainerOutputProgress.getId() +", workspaceId:" + workspaceId);
//        Document document = new Document();
//        document.put("trainer_progress", ddTrainerOutputProgress.getProgress());
//        document.put("percentage_done", ddTrainerOutputProgress.getPercentageDone());
//
//        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, workspaceId, "dd_trainer", document);
//

        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(WORKSPACE_COLLECTION_NAME);
        Bson filter = new BasicDBObject("_id", new ObjectId(workspaceId));
        Bson updatesModel1 = Updates.set("dd_trainer.percentage_done", ddTrainerOutputProgress.getPercentageDone());
        Bson updatesModel2 = Updates.set("dd_trainer.trainer_progress", ddTrainerOutputProgress.getProgress());
        Bson combinedUpdate = combine(updatesModel1, updatesModel2);
        collection.findOneAndUpdate(filter, combinedUpdate);

    }

    public void deleteProgress(String id){
        LOGGER.info("About to trainer saveProgress for jobId:" + id +", workspaceId:" + id);
        Document document = new Document();
        document.put("trainer_progress", "");
        document.put("percentage_done", 0.0);
        document.put("trainer_model", false);
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, id, "dd_trainer", document);
    }


    public void saveModelProgress(String jobId) {
        String workspaceId = crawlJobRepository.getWorkspaceId(jobId);
        LOGGER.info("About to trainer save Trainer saveModelProgress for jobId:" + jobId +", workspaceId:" + workspaceId);
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(WORKSPACE_COLLECTION_NAME);
        Bson filter = new BasicDBObject("_id", new ObjectId(workspaceId));
        Bson updatesModel = Updates.set("dd_trainer.trainer_model", Boolean.TRUE);
        collection.findOneAndUpdate(filter, updatesModel);
    }



}
