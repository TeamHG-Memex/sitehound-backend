package com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.translator;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.BroadCrawlContextDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tomas on 2/12/16.
 */
@Service
public class BroadCrawlContextDtoTranslator extends DefaultCrawlContextDtoTranslator{

	public Map<String, Object> translate(BroadCrawlContextDto broadCrawlContextDto){
		Map<String, Object> document = super.translate(broadCrawlContextDto);
		document.put("score", broadCrawlContextDto.getScore());
		return document;
	}
}
