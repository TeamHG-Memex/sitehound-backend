package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by tomas on 29/09/16.
 */

@Repository
public class DdCrawlerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdCrawlerRepository.class);

    @Autowired private MongoRepository mongoRepository;

    /*
    public void saveProgress(DdCrawlerOutputProgress ddCrawlerOutputProgress) {
        LOGGER.info("About to update crawler progress:" + ddCrawlerOutputProgress.getId());

        Bson filter = Filters.eq("_id", new ObjectId(ddCrawlerOutputProgress.getId()));
        Bson updateProgress = Updates.set("progress", ddCrawlerOutputProgress.getProgress());
        Bson updatePercentageDone = Updates.set("percentageDone", ddCrawlerOutputProgress.getPercentageDone());
        Bson combinedUpdate = combine(updateProgress, updatePercentageDone);

        MongoCollection<Document> collection = mongoRepository.getCollection(JOB_COLLECTION_NAME);
        collection.findOneAndUpdate(filter, combinedUpdate);

        LOGGER.info("updating field : " + ddCrawlerOutputProgress.getId() + " in: "+ JOB_COLLECTION_NAME);
    }
    */
}
