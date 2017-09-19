package com.hyperiongray.sitehound.backend.model;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

public class MetadataTestFactory {

    @Deprecated
    private static Metadata getMetadata_broadcrawl(){
        Metadata metadata = new Metadata();
        metadata.setWorkspace("test-workspace");
        metadata.setUow("uow-123435");
        metadata.setCrawlEntityType(Constants.CrawlEntityType.GOOGLE);
        metadata.setJobId("568a8649132ad21be932d3c2");
        metadata.setCrawlType(Constants.CrawlType.BROADCRAWL);
        metadata.setCallbackQueue("callbackqueue");
        metadata.setnResults(10);
        metadata.setTimestamp(System.currentTimeMillis());
        metadata.setStrTimestamp(Long.toString(System.currentTimeMillis()));
        return metadata;
    }

    public static Metadata getMetadataKeywords(){
        Metadata metadata = new Metadata();
        metadata.setWorkspace("test-workspace");
        metadata.setJobId("568a8649132ad21be932d3c2");
        metadata.setCrawlEntityType(Constants.CrawlEntityType.BING);
        metadata.setCrawlType(Constants.CrawlType.KEYWORDS);
        metadata.setCallbackQueue("callbackqueue");
        metadata.setUow("uow-12344321");
        metadata.setnResults(10);
        metadata.setTimestamp(System.currentTimeMillis());
        metadata.setStrTimestamp(Long.toString(System.currentTimeMillis()));
        return metadata;
    }
}
