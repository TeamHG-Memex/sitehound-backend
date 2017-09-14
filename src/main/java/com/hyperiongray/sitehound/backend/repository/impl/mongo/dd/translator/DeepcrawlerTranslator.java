package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DomainDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.ProgressDto;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DeepcrawlerTranslator {

    public Document translate(ProgressDto progress){
        Document document = new Document();
        document.put("status", progress.getStatus());
        document.put("pagesFetched", progress.getPagesFetched());
        document.put("rpm", progress.getRpm());

        List<Document> domains = new LinkedList();
        for(DomainDto domain : progress.getDomains()){
            Document d = new Document();
            d.put("url", domain.getUrl());
            d.put("domain", domain.getDomain());
            d.put("status", domain.getStatus());
            d.put("pagesFetched", domain.getPagesFetched());
            d.put("rpm", domain.getRpm());
            domains.add(d);
        }
        document.put("domains", domains);
        return document;
    }


}
