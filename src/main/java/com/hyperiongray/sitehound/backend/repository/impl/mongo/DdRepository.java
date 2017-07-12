package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import org.bson.Document;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputProgress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.translator.dd.DdModelerRepository.PAGE_MODEL_FIELD;

/**
 * Created by tomas on 29/09/16.
 */

@Repository
public class DdRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdRepository.class);

    @Autowired private CrawlJobRepository crawlJobRepository;
    @Autowired private MongoRepository mongoRepository;


    public static final String WORKSPACE_COLLECTION_NAME = "workspace";
    private static final String LINK_MODEL_FIELD = "link_model";
    private static final String TRAINER_PROGRESS_FIELD = "trainer_progress";



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

        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, workspaceId, "dd_trainer", document);
    }

    public void saveProgress(DdCrawlerOutputProgress ddCrawlerOutputProgress) {
        LOGGER.info("About to crawler saveProgress:" + ddCrawlerOutputProgress.getId());
        Document document = new Document();
        document.put("crawler_progress", ddCrawlerOutputProgress.getProgress());
        String workspaceId = crawlJobRepository.getWorkspaceId(ddCrawlerOutputProgress.getId());
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, workspaceId, "dd_crawler", document);
    }


    public Map<String, String> getModels(String workspaceId){

        Document document = mongoRepository.getById(WORKSPACE_COLLECTION_NAME, workspaceId);
        Map<String, String> keyValues = new HashMap<>();

        if(document.containsKey(PAGE_MODEL_FIELD)) {
            Document modelerDocument = (Document) document.get(PAGE_MODEL_FIELD);
            String page_model = modelerDocument.getString("model");
            keyValues.put("page_model", page_model);
        }
        else{
            throw new IllegalStateException(PAGE_MODEL_FIELD + "was empty for workspaceId" + workspaceId);
        }

        if(document.containsKey(LINK_MODEL_FIELD)) {
            Document modelerDocument = (Document) document.get(LINK_MODEL_FIELD);
            String link_model = modelerDocument.getString("model");
            keyValues.put("link_model", link_model);
        }
        else{
            throw new IllegalStateException(LINK_MODEL_FIELD + "was empty for workspaceId" + workspaceId);
        }

        return keyValues;
    }
}
