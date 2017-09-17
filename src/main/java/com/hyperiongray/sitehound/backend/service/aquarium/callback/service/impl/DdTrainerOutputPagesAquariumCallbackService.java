package com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.*;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlRequestTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlResultTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.GenericCrawlMongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.translator.DefaultCrawlContextDtoTranslator;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DdTrainerOutputPagesAquariumCallbackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DdTrainerOutputPagesAquariumCallbackService.class);

    @Autowired private CrawlRequestTranslator crawlRequestTranslator;
    @Autowired private CrawlResultTranslator crawlResultTranslator;
    @Autowired private CrawledIndexRepository analyzedCrawlResultDtoIndexRepository;
    @Autowired private GenericCrawlMongoRepository genericCrawlMongoRepository;
    @Autowired private DefaultCrawlContextDtoTranslator defaultCrawlContextDtoTranslator;

    public void process(PageSample pageSample, Metadata metadata, AquariumInternal aquariumInternal) {
        try{
            CrawlRequestDto crawlRequestDto = crawlRequestTranslator.fromMetadata(pageSample.getUrl(), metadata);

            CrawlResultDto crawlResultDto = crawlResultTranslator.translateFromAquariumInternal(aquariumInternal);
            AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);

            // update ES index
            String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(pageSample.getUrl(), crawlRequestDto.getWorkspace(), crawlRequestDto.getCrawlEntityType(), analyzedCrawlResultDto);

            // update mongodb
            Map<String, Object> document = defaultCrawlContextDtoTranslator.translate(hashKey, crawlRequestDto, analyzedCrawlResultDto, pageSample.getScore());
            genericCrawlMongoRepository.save(crawlRequestDto.getCrawlType(), crawlRequestDto.getWorkspace(), document);

        }catch(Exception e){
            LOGGER.error("Error Analyzing: " + pageSample.getUrl(), e);
        }
    }

}
