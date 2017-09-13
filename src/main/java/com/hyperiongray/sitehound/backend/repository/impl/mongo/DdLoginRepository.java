package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.model.CrawlTask;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.model.DdLoginResult;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.dd.login.DdLoginResultBrokerService;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 22/06/17.
 */
@Repository
public class DdLoginRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdLoginRepository.class);

    private static final String LOGIN_COLLECTION_NAME = "login";

    @Autowired private MongoRepository mongoRepository;

    public void save(DdLoginInput ddLoginInput){
        LOGGER.info("About to save ddLogin:" + ddLoginInput);

        Document document = new Document();
        document.putAll(entityToFields(ddLoginInput));

        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(LOGIN_COLLECTION_NAME);
        try {
            collection.insertOne(document);
            LOGGER.info("Saved ddLogin");
        }
        catch (MongoWriteException monoWriteException){
            if(monoWriteException.getCode()==11000){
                LOGGER.warn("Skipping duplicate: " + ddLoginInput);
            }
            else{
                LOGGER.error("save failed", monoWriteException);
            }
        }
    }

    private Map<String, Object> entityToFields(DdLoginInput ddLoginInput){
        Map<String, Object> document = Maps.newHashMap();
        document.put("workspaceId", ddLoginInput.getWorkspaceId());
        document.put("jobId", ddLoginInput.getJobId());
        document.put("url", ddLoginInput.getUrl());
        document.put("domain", ddLoginInput.getDomain());
//        document.put("screenshot", ddLoginInput.getScreenshot());
        document.put("keyValues", ddLoginInput.getKeyValues());
        document.put("keysOrder", ddLoginInput.getKeysOrder());
        return document;
    }

    public boolean updateResult(DdLoginResult ddLoginResult) {
        LOGGER.info("About to update login results:" + ddLoginResult.getId());
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(LOGIN_COLLECTION_NAME);
//		Bson filter = new BasicDBObject("_id", new ObjectId(jobId)).append("status", "QUEUED");
        Bson filter = new BasicDBObject("_id", new ObjectId(ddLoginResult.getId()));
        UpdateResult updateResult=collection.updateOne(filter,
                new BasicDBObject("$set", new BasicDBObject("result", ddLoginResult.getResult())));
        LOGGER.info("Finished to update crawlJob:" + ddLoginResult.getId() + ": " + ddLoginResult.getResult());
        return updateResult.getModifiedCount() == 1L;
    }


    public DdLoginInput getByDomain(String domain){
        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(LOGIN_COLLECTION_NAME);
        Bson filter = new BasicDBObject("domain", domain);
        FindIterable<Document> iterable = collection.find(filter);
        Document document = iterable.iterator().next();
        DdLoginInput ddLoginInput = new DdLoginInput.Builder()
                .withId( document.getObjectId("_id").toString())
                .withUrl(document.getString("url"))
                .withDomain(document.getString("domain"))
                .withWorkspaceId(document.getString("workspaceId"))
                .withJobId(document.getString("jobId"))
                .withKeysOrder((List<String> )document.get("keysOrder"))
                .withKeyValues((Map<String,String>)document.get("keyValues"))
                .withResult(document.getString("result"))
                .build();

        return ddLoginInput;
    }
}
