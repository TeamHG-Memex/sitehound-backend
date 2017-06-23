package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.model.CrawlTask;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by tomas on 22/06/17.
 */
@Repository
public class DdLoginRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdLoginRepository.class);

    private static final String LOGIN_COLLECTION_NAME = "login_input";

    @Autowired private MongoRepository mongoRepository;

    public void save(DdLoginInput ddLoginInput){
        LOGGER.info("About to save ddLogin:" + ddLoginInput);

        Document document = new Document();
        document.putAll(entityToFields(ddLoginInput));

        MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(LOGIN_COLLECTION_NAME);
        collection.insertOne(document);
        LOGGER.info("Saved ddLogin");
    }

    private Map<String, Object> entityToFields(DdLoginInput ddLoginInput){
        Map<String, Object> document = Maps.newHashMap();
        document.put("workspaceId", ddLoginInput.getWorkspaceId());
        document.put("jobId", ddLoginInput.getJobId());
        document.put("url", ddLoginInput.getUrl());
        document.put("screenshot", ddLoginInput.getScreenshot());
        document.put("keyValues", ddLoginInput.getKeyValues());
        document.put("keysOrder", ddLoginInput.getKeysOrder());
        return document;
    }
}