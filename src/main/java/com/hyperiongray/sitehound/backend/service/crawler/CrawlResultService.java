package com.hyperiongray.sitehound.backend.service.crawler;

import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.BroadCrawlContextDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.TrainingCrawlContextDto;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.GenericCrawlMongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.translator.BroadCrawlContextDtoTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.translator.DefaultCrawlContextDtoTranslator;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator.TrainingCrawlContextDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tomas on 2/9/16.
 */
@Service
public class CrawlResultService{

	@Autowired private CrawledIndexRepository analyzedCrawlResultDtoIndexRepository;
	@Autowired private GenericCrawlMongoRepository genericCrawlMongoRepository;
	@Autowired private TrainingCrawlContextDtoTranslator trainingCrawlContextDtoTranslator;
	@Autowired private BroadCrawlContextDtoTranslator broadCrawlContextDtoTranslator;

	public void save(BroadCrawlContextDto broadCrawlContextDto) throws IOException{

		// update ES index
		String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(broadCrawlContextDto.getCrawlRequestDto().getUrl(), broadCrawlContextDto.getCrawlRequestDto().getWorkspace(), broadCrawlContextDto.getCrawlRequestDto().getCrawlEntityType(), broadCrawlContextDto.getAnalyzedCrawlResultDto());

		// update mongo index
		Map<String, Object> document = broadCrawlContextDtoTranslator.translate(broadCrawlContextDto);

		document.put("hashKey", hashKey);
		genericCrawlMongoRepository.save(broadCrawlContextDto.getCrawlType(), broadCrawlContextDto.getCrawlRequestDto().getWorkspace(), document);
	}

	public void save(TrainingCrawlContextDto trainingCrawlContextDto) throws IOException{

		// update ES index
		String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(trainingCrawlContextDto.getCrawlRequestDto().getUrl(), trainingCrawlContextDto.getCrawlRequestDto().getWorkspace(), trainingCrawlContextDto.getCrawlRequestDto().getCrawlEntityType(), trainingCrawlContextDto.getAnalyzedCrawlResultDto());

		// update mongo index
		Map<String, Object> document = trainingCrawlContextDtoTranslator.translate(trainingCrawlContextDto);
		document.put("hashKey", hashKey);
		genericCrawlMongoRepository.save(trainingCrawlContextDto.getCrawlType(), trainingCrawlContextDto.getCrawlRequestDto().getWorkspace(), document);
	}

//
//	public void save(DeepCrawlContextDto deepCrawlContextDto) throws IOException {
//		// update ES index
//		String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(deepCrawlContextDto.getCrawlRequestDto().getUrl(), deepCrawlContextDto.getCrawlRequestDto().getWorkspace(), deepCrawlContextDto.getCrawlRequestDto().getCrawlEntityType(), deepCrawlContextDto.getAnalyzedCrawlResultDto());
//
//		// update mongo index
//		Map<String, Object> document = broadCrawlContextDtoTranslator.translate(deepCrawlContextDto);
//		document.put("hashKey", hashKey);
//		genericCrawlMongoRepository.save(deepCrawlContextDto.getCrawlType(), deepCrawlContextDto.getCrawlRequestDto().getWorkspace(), document);
//
//	}
}
