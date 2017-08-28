package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.DdDeepcrawlerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DdDeepcrawlerProgress;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.Domain;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.Progress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.CrawlJobRepository.CRAWL_JOB_COLLECTION_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.DEEP_CRAWLER_COLLECTION_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.DEEP_CRAWLER_DOMAINS_COLLECTION_NAME;

/**
 * Created by tomas on 27/08/17.
 */
@Repository
public class DdDeepcrawlerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerRepository.class);

    @Autowired private CrawlJobRepository crawlJobRepository;
    @Autowired private MongoRepository mongoRepository;
    private static final String PROGRESS_FIELD = "progress";

    public boolean saveProgress(DdDeepcrawlerProgress ddDeepcrawlerProgress) {
        String jobId = ddDeepcrawlerProgress.getId();
        LOGGER.info("About to update crawlJob:" + jobId);
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(CRAWL_JOB_COLLECTION_NAME);
        Bson filter = new BasicDBObject("_id", new ObjectId(jobId));
        UpdateResult updateResult=collection.updateOne(filter,
                new BasicDBObject("$set", new BasicDBObject(PROGRESS_FIELD, translate(ddDeepcrawlerProgress.getProgress()))));
        LOGGER.info("Finished to update crawlJob:" + jobId);
        return updateResult.getModifiedCount() == 1L;
    }

    private Document translate(Progress progress){
        Document document = new Document();
        document.put("status", progress.getStatus());
        document.put("pagesFetched", progress.getPagesFetched());
        document.put("rpm", progress.getRpm());

        List<Document> domains = new LinkedList();
        for(Domain domain : progress.getDomains()){
            Document d = new Document();
            d.put("url", domain.getUrl());
            d.put("domain", domain.getDomain());
            d.put("pagesFetched", domain.getPagesFetched());
            d.put("finished", domain.getFinished());
            d.put("rpm", domain.getRpm());
            domains.add(d);
        }
        document.put("domains", domains);
        return document;
    }

    public void saveDomains(Map<String, Object> fields){
        mongoRepository.insert(DEEP_CRAWLER_DOMAINS_COLLECTION_NAME, fields);
    }

    public void savePages(Map<String, Object> fields){
        mongoRepository.insert(DEEP_CRAWLER_COLLECTION_NAME, fields);
    }


}
