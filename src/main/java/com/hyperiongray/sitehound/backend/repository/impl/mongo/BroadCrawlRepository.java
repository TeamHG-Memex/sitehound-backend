package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by tomas on 9/06/17.
 */
@Repository
public class BroadCrawlRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlJobRepository.class);

    private static final String BROAD_CRAWLER_COLLECTION_NAME = "broad_crawler";

    @Autowired private MongoRepository mongoRepository;

    public String getUrl(String docId){
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(BROAD_CRAWLER_COLLECTION_NAME);
        Bson filter = new BasicDBObject("_id", new ObjectId(docId));
        FindIterable<Document> iterable = collection.find(filter);
        return iterable.iterator().next().getString("url");
    }
}
