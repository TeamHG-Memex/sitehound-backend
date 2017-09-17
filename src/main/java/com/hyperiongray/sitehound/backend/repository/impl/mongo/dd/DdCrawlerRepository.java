package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.WORKSPACE_COLLECTION_NAME;

/**
 * Created by tomas on 29/09/16.
 */

@Repository
public class DdCrawlerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdCrawlerRepository.class);

//    @Autowired private CrawlJobRepository crawlJobRepository;
    @Autowired private MongoRepository mongoRepository;

    public void saveProgress(DdCrawlerOutputProgress ddCrawlerOutputProgress) {
        LOGGER.info("About to crawler saveProgress:" + ddCrawlerOutputProgress.getId());
        Document document = new Document();
        document.put("progress", ddCrawlerOutputProgress.getProgress());
        document.put("percentageDone", ddCrawlerOutputProgress.getPercentageDone());
//        String workspaceId = crawlJobRepository.getWorkspaceId(ddCrawlerOutputProgress.getId());
        mongoRepository.updateFieldsInDocument(WORKSPACE_COLLECTION_NAME, ddCrawlerOutputProgress.getWorkspaceId(), "dd_crawler", document);
    }

}
