package com.hyperiongray.sitehound.backend.service.crawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberOutput;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 7/27/15.
 */
public abstract class GenericCrawlResultTranslator implements CrawlResultTranslator{

	@Override
	public List<SubscriberOutput> toDtos(List<? extends CrawlResult> crawlResults){
		List<SubscriberOutput> subscriberOutputs = new LinkedList<>();
		for (CrawlResult crawlResult: crawlResults){
			subscriberOutputs.add(this.toDto(crawlResult));
		}
		return subscriberOutputs;
	}
}
