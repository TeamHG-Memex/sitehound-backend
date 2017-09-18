package com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlRequestDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlRequestTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlResultTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.GenericCrawlMongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.translator.DefaultCrawlContextDtoTranslator;
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
public class DefaultAquariumCallbackService{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAquariumCallbackService.class);

    @Autowired private CrawlRequestTranslator crawlRequestTranslator;
    @Autowired private CrawlResultTranslator crawlResultTranslator;
    @Autowired private CrawledIndexRepository analyzedCrawlResultDtoIndexRepository;
    @Autowired private GenericCrawlMongoRepository genericCrawlMongoRepository;
    @Autowired private DefaultCrawlContextDtoTranslator defaultCrawlContextDtoTranslator;


    public void process(AquariumInput aquariumInput, AquariumInternal aquariumInternal){

        try{
            CrawlRequestDto crawlRequestDto = crawlRequestTranslator.fromAquariumInput(aquariumInput);
            CrawlResultDto crawlResultDto = crawlResultTranslator.translateFromAquariumInternal(aquariumInternal);
            AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);

            // update ES index
            String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(crawlRequestDto.getUrl(), crawlRequestDto.getWorkspace(), crawlRequestDto.getCrawlEntityType(), analyzedCrawlResultDto);

            Map<String, Object> document = defaultCrawlContextDtoTranslator.translate(hashKey, crawlRequestDto, analyzedCrawlResultDto);
            if(crawlRequestDto.getCrawlEntityType().equals(Constants.CrawlEntityType.MANUAL)){
                document.put("relevant", true);
            }
            genericCrawlMongoRepository.save(crawlRequestDto.getCrawlType(), crawlRequestDto.getWorkspace(), document);


        }catch(Exception e){
            LOGGER.error("Error Analyzing: " + aquariumInput.getUrl(), e);
        }
    }


}
