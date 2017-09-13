package com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.model.DeepcrawlerPageRequest;
import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.*;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.AnalyzedCrawlRequestFactory;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlRequestTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlResultTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.GenericCrawlMongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdDeepcrawlerRepository;
import com.hyperiongray.sitehound.backend.service.CrawlResultService;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tomas on 3/10/16.
 */
@Service
public class DdDeepcrawlerOutputPagesAquariumCallbackService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerOutputPagesAquariumCallbackService.class);

    @Autowired private CrawlRequestTranslator crawlRequestTranslator;
    @Autowired private CrawlResultTranslator crawlResultTranslator;
    @Autowired private AnalyzedCrawlRequestFactory analyzedCrawlRequestFactory;
    @Autowired private CrawlResultService crawlResultService;
    @Autowired private CrawledIndexRepository analyzedCrawlResultDtoIndexRepository;
    @Autowired private GenericCrawlMongoRepository genericCrawlMongoRepository;
    @Autowired private DdDeepcrawlerRepository ddDeepcrawlerRepository;


    public void process(CrawlJob crawlJob, DeepcrawlerPageRequest deepcrawlerPageRequest, AquariumInternal aquariumInternal){

        try{
            CrawlResultDto crawlResultDto = crawlResultTranslator.translateFromAquariumInternal(aquariumInternal);
            AnalyzedCrawlResultDto analyzedCrawlResultDto = analyzedCrawlRequestFactory.build(crawlResultDto);
            // update ES index
            String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(deepcrawlerPageRequest.getUrl(), crawlJob.getWorkspaceId(), crawlJob.getCrawlEntityType(), analyzedCrawlResultDto);

            // update mongo index
            Map<String, Object> document = Maps.newHashMap();
            document.put("workspaceId", crawlJob.getWorkspaceId());
            document.put("jobId", crawlJob.getJobId());
            document.put("timestamp", crawlResultDto.getTimestamp());
            document.put("title", crawlResultDto.getTitle());
            document.put("url", deepcrawlerPageRequest.getUrl().toLowerCase());
            document.put("domain", deepcrawlerPageRequest.getDomain());
            document.put("hashKey", hashKey);

            if(deepcrawlerPageRequest.getIsHome()) {
                ddDeepcrawlerRepository.saveDomains(document);
            }
            else{
                ddDeepcrawlerRepository.savePages(document);
            }

        }catch(Exception e){
            LOGGER.error("Error Analyzing: " + deepcrawlerPageRequest.getUrl(), e);
        }
    }


}
