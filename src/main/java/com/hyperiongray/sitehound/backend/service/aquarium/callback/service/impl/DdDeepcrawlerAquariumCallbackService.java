package com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.*;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.AnalyzedCrawlRequestFactory;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlRequestTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlResultTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.GenericCrawlMongoRepository;
import com.hyperiongray.sitehound.backend.service.CrawlResultService;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tomas on 3/10/16.
 */
@Service
public class DdDeepcrawlerAquariumCallbackService{
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerAquariumCallbackService.class);

    @Autowired private CrawlRequestTranslator crawlRequestTranslator;
    @Autowired private CrawlResultTranslator crawlResultTranslator;
    @Autowired private AnalyzedCrawlRequestFactory analyzedCrawlRequestFactory;
    @Autowired private CrawlResultService crawlResultService;
    @Autowired private CrawledIndexRepository analyzedCrawlResultDtoIndexRepository;
    @Autowired private GenericCrawlMongoRepository genericCrawlMongoRepository;

    public void process(AquariumInput aquariumInput, AquariumInternal aquariumInternal, String domain){

        try{
            CrawlRequestDto crawlRequestDto = crawlRequestTranslator.fromAquariumInput(aquariumInput);
            CrawlResultDto crawlResultDto = crawlResultTranslator.translateFromAquariumInternal(aquariumInternal);
            AnalyzedCrawlResultDto analyzedCrawlResultDto = analyzedCrawlRequestFactory.build(crawlResultDto);

            DeepCrawlContextDto deepCrawlContextDto = new DeepCrawlContextDto(crawlRequestDto, analyzedCrawlResultDto);
/*
            // update ES index
            String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(crawlRequestDto.getUrl(), crawlRequestDto.getWorkspace(), crawlRequestDto.getCrawlEntityType(), analyzedCrawlResultDto);

            // update mongo index
            Map<String, Object> document = translate(crawlRequestDto);
            document.put("hashKey", hashKey);
            genericCrawlMongoRepository.save(deepCrawlContextDto.getCrawlType(), deepCrawlContextDto.getCrawlRequestDto().getWorkspace(), document);

*/
            crawlResultService.save(deepCrawlContextDto);

        }catch(Exception e){
            LOGGER.error("Error Analyzing: " + aquariumInput.getUrl(), e);
        }
    }
/*
    public Map<String, Object> translate(CrawlRequestDto crawlRequestDto){
        Map<String, Object> document = Maps.newHashMap();
        document.put("workspaceId", crawlRequestDto.getWorkspace());
        document.put("jobId", crawlRequestDto.getJobId());
        document.put("timestamp", crawlRequestDto.getTimestamp());
        document.put("provider", Constants.CrawlerProvider.HH_JOOGLE.toString());
        document.put("crawlEntityType", crawlContextDto.getCrawlRequestDto().getCrawlEntityType().toString());
        document.put("url", crawlContextDto.getCrawlRequestDto().getUrl().toLowerCase());
        document.put("host", crawlContextDto.getAnalyzedCrawlResultDto().getCrawlResultDto().getHost().toLowerCase());
        document.put("language", crawlContextDto.getAnalyzedCrawlResultDto().getLanguage()); // this is also stored in Elasticsearch
        document.put("categories", crawlContextDto.getAnalyzedCrawlResultDto().getCategories());  // this is also stored in Elasticsearch
        document.put("words", crawlContextDto.getAnalyzedCrawlResultDto().getWords());  // this is also stored in Elasticsearch
        document.put("title", crawlContextDto.getAnalyzedCrawlResultDto().getCrawlResultDto().getTitle());  // this is also stored in Elasticsearch
        return document;
    }
*/


}
