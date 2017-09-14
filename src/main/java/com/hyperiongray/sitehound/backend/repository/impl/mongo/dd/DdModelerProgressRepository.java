package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
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

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.WORKSPACE_COLLECTION_NAME;


/**
 * Created by tomas on 12/07/17.
 */
@Repository
public class DdModelerProgressRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerProgressRepository.class);
    private static final String PAGE_MODEL_FIELD = "page_model";

    @Autowired private MongoRepository mongoRepository;



    public void saveProgress(DdModelerProgress ddModelerProgress) {
        try{
            LOGGER.info("About to save ddModelerProgress:" + ddModelerProgress.getId());
            MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(WORKSPACE_COLLECTION_NAME);
            Bson filter = Filters.eq("_id", new ObjectId(ddModelerProgress.getId()));
            Bson updates = Updates.set(PAGE_MODEL_FIELD + ".percentage_done", ddModelerProgress.getPercentageDone());
            collection.findOneAndUpdate(filter, updates);
            LOGGER.info("done saving ddModelerProgress");
        }
        catch (Throwable ex){
            LOGGER.error("FAILED TO SAVE" + ddModelerProgress, ex);
        }
    }


    public DdModelerProgress getProgress(String workspaceId){
        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setId(workspaceId);
        Document document = mongoRepository.getById(WORKSPACE_COLLECTION_NAME, workspaceId);
        if(document.containsKey(PAGE_MODEL_FIELD)){
            Document modelerDocument = (Document) document.get(PAGE_MODEL_FIELD);
            Double modelerProgress = modelerDocument.getDouble("percentage_done");
            ddModelerProgress.setPercentageDone(modelerProgress);
        }
        LOGGER.info("getting save DdModelerProgress:" + ddModelerProgress);
        return ddModelerProgress;
    }

    public void deleteProgress(String workspaceId){
        LOGGER.info("About to delete ddModelerProgress modeler from :" + workspaceId);
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(WORKSPACE_COLLECTION_NAME);
        Bson filter = Filters.eq("_id", new ObjectId(workspaceId));
        Bson updatesProgress = Updates.set(PAGE_MODEL_FIELD + ".percentage_done", 0.0);
        collection.findOneAndUpdate(filter, updatesProgress);
    }


}
