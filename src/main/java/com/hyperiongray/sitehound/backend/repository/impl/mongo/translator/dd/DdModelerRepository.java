package com.hyperiongray.sitehound.backend.repository.impl.mongo.translator.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository.WORKSPACE_COLLECTION_NAME;


/**
 * Created by tomas on 12/07/17.
 */
@Repository
public class DdModelerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerRepository.class);

    @Autowired private MongoRepository mongoRepository;
    @Autowired private CrawlJobRepository crawlJobRepository;

    public static final String PAGE_MODEL_FIELD = "page_model";
    private static final String PAGE_MODEL_PROGRESS_FIELD = "dd_modeler";

    public void savePageModel(DdModelerOutput ddModelerOutput){
        LOGGER.info("About to savePageModel:" + ddModelerOutput.getId());

        Document document = new Document();
        document.put("quality", ddModelerOutput.getQuality());
        document.put("model", ddModelerOutput.getModel());
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, ddModelerOutput.getId(), PAGE_MODEL_FIELD, document);
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


    public void saveProgress(DdModelerProgress ddModelerProgress) {
        LOGGER.info("About save ddModelerProgress:" + ddModelerProgress.getId());
        Document document = new Document();
        document.put("modeler_progress", ddModelerProgress.getPercentageDone());
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, ddModelerProgress.getId(), PAGE_MODEL_PROGRESS_FIELD, document);
    }


    public DdModelerProgress getProgress(String workspaceId){
        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setId(workspaceId);
        Document document = mongoRepository.getById(WORKSPACE_COLLECTION_NAME, workspaceId);
        if(document.containsKey(PAGE_MODEL_PROGRESS_FIELD)){
            Document modelerDocument = (Document) document.get(PAGE_MODEL_PROGRESS_FIELD);
            String modelerProgress = modelerDocument.getString("modeler_progress");
            ddModelerProgress.setPercentageDone(modelerProgress);
        }
        LOGGER.info("getting save DdModelerProgress:" + ddModelerProgress);
        return ddModelerProgress;
    }


}
