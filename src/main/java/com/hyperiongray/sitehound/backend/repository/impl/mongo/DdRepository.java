package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import org.bson.Document;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputProgress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomas on 29/09/16.
 */

@Repository
public class DdRepository {


    @Autowired private CrawlJobRepository crawlJobRepository;

    @Autowired private MongoRepository mongoRepository;

    private static final String WORKSPACE_COLLECTION_NAME = "workspace";
    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlTaskRepository.class);

    private static final String PAGE_MODEL_FIELD = "page_model";
    private static final String LINK_MODEL_FIELD = "link_model";
    private static final String TRAINER_PROGRESS_FIELD = "trainer_progress";


    public void savePageModel(DdModelerOutput ddModelerOutput){
        LOGGER.info("About to savePageModel:" + ddModelerOutput.getId());

        Document document = new Document();
        document.put("quality", ddModelerOutput.getQuality());
        document.put("model", ddModelerOutput.getModel());
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, ddModelerOutput.getId(), PAGE_MODEL_FIELD, document);
    }

    public void saveLinkModel(DdTrainerOutputModel ddTrainerOutputModel){
        LOGGER.info("About to saveLinkModel:" + ddTrainerOutputModel.getId());
        Document document = new Document();
        document.put("model", ddTrainerOutputModel.getLink_model());
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, ddTrainerOutputModel.getId(), LINK_MODEL_FIELD, document);
    }


    public DdModelerOutput getPageModel(String workspaceId){
        DdModelerOutput ddModelerOutput = null;
        Document document = mongoRepository.getById(WORKSPACE_COLLECTION_NAME, workspaceId);
        if(document.containsKey(PAGE_MODEL_FIELD)){
            Document modelerDocument = (Document) document.get(PAGE_MODEL_FIELD);
            String quality = modelerDocument.getString("quality");
            String model = modelerDocument.getString("model");
            ddModelerOutput = new DdModelerOutput(workspaceId, quality, model);
        }
        return  ddModelerOutput;
    }


    public void saveProgress(DdTrainerOutputProgress ddTrainerOutputProgress) {
        LOGGER.info("About to trainer saveProgress:" + ddTrainerOutputProgress.getId());
        Document document = new Document();
        document.put("trainer_progress", ddTrainerOutputProgress.getProgress());
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, ddTrainerOutputProgress.getId(), "dd_trainer", document);
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
