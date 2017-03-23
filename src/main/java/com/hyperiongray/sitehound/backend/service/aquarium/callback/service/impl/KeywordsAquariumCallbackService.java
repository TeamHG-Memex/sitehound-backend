package com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl;

import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlRequestDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.TrainingCrawlContextDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.AnalyzedCrawlRequestFactory;
import com.hyperiongray.sitehound.backend.service.CrawlResultService;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlRequestTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator.CrawlResultTranslator;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.DefaultProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 3/10/16.
 */
@Service
public class KeywordsAquariumCallbackService implements DefaultProcess{

    @Autowired
    private CrawlRequestTranslator crawlRequestTranslator;
    @Autowired private CrawlResultTranslator crawlResultTranslator;
    @Autowired private AnalyzedCrawlRequestFactory analyzedCrawlRequestFactory;
//    @Autowired private ScorerService scorerService;
    @Autowired private CrawlResultService crawlResultService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsAquariumCallbackService.class);

    public void process(AquariumInput aquariumInput, AquariumInternal aquariumInternal){

        try{
            CrawlRequestDto crawlRequestDto = crawlRequestTranslator.fromAquariumInput(aquariumInput);
            CrawlResultDto crawlResultDto = crawlResultTranslator.translateFromAquariumInternal(aquariumInternal);
            AnalyzedCrawlResultDto analyzedCrawlResultDto = analyzedCrawlRequestFactory.build(crawlResultDto);

//            if(aquariumInput.getMetadata().getCrawlType()== Constants.CrawlType.BROADCRAWL){
//                Double score;
//                try{
//                    score = scorerService.score(crawlRequestDto.getWorkspace(), crawlRequestDto.getJobId(), crawlRequestDto.getUrl(), analyzedCrawlResultDto.getText());
//                }
//                catch (Exception e) {
//                    LOGGER.error("ERROR scoring:" +aquariumInput, e);
//                    score = 0d;
//                }
//
//                BroadCrawlContextDto broadCrawlContextDto = new BroadCrawlContextDto(crawlRequestDto, analyzedCrawlResultDto, score);
//                crawlResultService.save(broadCrawlContextDto);
//            }
//            else if(aquariumInput.getMetadata().getCrawlType()== Constants.CrawlType.KEYWORDS){
                TrainingCrawlContextDto trainingCrawlContextDto = new TrainingCrawlContextDto(crawlRequestDto, analyzedCrawlResultDto);
                crawlResultService.save(trainingCrawlContextDto);
//            }
//            else {
//                throw new RuntimeException("UNSUPPORTED CRAWLTYPE:" +aquariumInput.getMetadata().toString());
//            }

        }catch(Exception e){
            LOGGER.error("Error Analyzing: " + aquariumInput.getUrl(), e);
        }
    }


}
