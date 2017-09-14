package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.TrainingCrawlContextDto;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.translator.AbstractCrawlContextDtoTranslator;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tomas on 2/12/16.
 */
@Service
public class TrainingCrawlContextDtoTranslator extends AbstractCrawlContextDtoTranslator {

	public Map<String, Object> translate(TrainingCrawlContextDto trainingCrawlContext){

		Map<String, Object> document = super.translate(trainingCrawlContext);
		if(trainingCrawlContext.getCrawlRequestDto().getCrawlEntityType().equals(Constants.CrawlEntityType.MANUAL)){
			document.put("relevant", true);
		}
		return document;
	}
}
