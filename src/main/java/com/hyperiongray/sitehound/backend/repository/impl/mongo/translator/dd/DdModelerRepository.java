package com.hyperiongray.sitehound.backend.repository.impl.mongo.translator.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.DdRepository.WORKSPACE_COLLECTION_NAME;
import static com.mongodb.client.model.Updates.combine;


/**
 * Created by tomas on 12/07/17.
 */
@Repository
public class DdModelerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerRepository.class);

    @Autowired private MongoRepository mongoRepository;
    @Autowired private CrawlJobRepository crawlJobRepository;

    public static final String PAGE_MODEL_FIELD = "page_model";

    public void savePageModel(DdModelerOutput ddModelerOutput){
        LOGGER.info("About to savePageModel:" + ddModelerOutput.getId());
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(WORKSPACE_COLLECTION_NAME);
        Bson filter = Filters.eq("_id", new ObjectId(ddModelerOutput.getId()));
        Bson updateQuality = Updates.set(PAGE_MODEL_FIELD + ".quality", ddModelerOutput.getQuality());
        Bson updatesModel = Updates.set(PAGE_MODEL_FIELD + ".model", ddModelerOutput.getModel());
        Bson combinedUpdate = combine(updateQuality, updatesModel);
        collection.findOneAndUpdate(filter, combinedUpdate);
        LOGGER.info("done saving DdModelerOutput");
    }

    public void deletePageModel(String workspaceId){
        LOGGER.info("About to delete ddModelerProgress modeler from :" + workspaceId);
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(WORKSPACE_COLLECTION_NAME);
        Bson filter = Filters.eq("_id", new ObjectId(workspaceId));
        Bson updateQuality = Updates.unset(PAGE_MODEL_FIELD + ".quality");
        Bson updatesModel = Updates.unset(PAGE_MODEL_FIELD + ".model");
        Bson updatesProgress = Updates.unset(PAGE_MODEL_FIELD + ".progress");
        Bson combinedUpdate = combine(updateQuality, updatesModel, updatesProgress);
        collection.findOneAndUpdate(filter, combinedUpdate);
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
        LOGGER.info("About to save ddModelerProgress:" + ddModelerProgress.getId());
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(WORKSPACE_COLLECTION_NAME);
        Bson filter = Filters.eq("_id", new ObjectId(ddModelerProgress.getId()));
        Bson updates = Updates.set(PAGE_MODEL_FIELD + ".progress", ddModelerProgress.getPercentageDone());
        collection.findOneAndUpdate(filter, updates);
        LOGGER.info("done saving ddModelerProgress");
    }


    public DdModelerProgress getProgress(String workspaceId){
        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setId(workspaceId);
        Document document = mongoRepository.getById(WORKSPACE_COLLECTION_NAME, workspaceId);
        if(document.containsKey(PAGE_MODEL_FIELD)){
            Document modelerDocument = (Document) document.get(PAGE_MODEL_FIELD);
            Double modelerProgress = modelerDocument.getDouble("progress");
            ddModelerProgress.setPercentageDone(modelerProgress);
        }
        LOGGER.info("getting save DdModelerProgress:" + ddModelerProgress);
        return ddModelerProgress;
    }


}
