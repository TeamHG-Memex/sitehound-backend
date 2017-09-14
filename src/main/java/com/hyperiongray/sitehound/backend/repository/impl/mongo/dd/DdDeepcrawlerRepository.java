package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DdDeepcrawlerProgressDto;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator.DeepcrawlerTranslator;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.DEEP_CRAWLER_COLLECTION_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.DEEP_CRAWLER_DOMAINS_COLLECTION_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository.CRAWL_JOB_COLLECTION_NAME;

/**
 * Created by tomas on 27/08/17.
 */
@Repository
public class DdDeepcrawlerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerRepository.class);

    @Autowired private MongoRepository mongoRepository;

    @Autowired private DeepcrawlerTranslator deepcrawlerTranslator;

    private static final String PROGRESS_FIELD = "progress";

    public boolean saveProgress(DdDeepcrawlerProgressDto ddDeepcrawlerProgressDto) {
        String jobId = ddDeepcrawlerProgressDto.getId();
        LOGGER.info("About to update crawlJob:" + jobId);
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(CRAWL_JOB_COLLECTION_NAME);
        Bson filter = new BasicDBObject("_id", new ObjectId(jobId));
        UpdateResult updateResult=collection.updateOne(filter,
                new BasicDBObject("$set", new BasicDBObject(PROGRESS_FIELD, deepcrawlerTranslator.translate(ddDeepcrawlerProgressDto.getProgress()))));
        LOGGER.info("Finished to update crawlJob:" + jobId);
        return updateResult.getModifiedCount() == 1L;
    }

    public void saveDomains(Map<String, Object> fields){
        mongoRepository.insert(DEEP_CRAWLER_DOMAINS_COLLECTION_NAME, fields);
    }

    public void savePages(Map<String, Object> fields){
        mongoRepository.insert(DEEP_CRAWLER_COLLECTION_NAME, fields);
    }

}
