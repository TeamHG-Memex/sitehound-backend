package com.hyperiongray.sitehound.backend.service.crawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberOutput;

import java.util.List;

/**
 * Created by tomas on 7/27/15.
 */
public interface CrawlResultTranslator{

	List<SubscriberOutput> toDtos(List<? extends CrawlResult> crawlResults);

	<T extends CrawlResult> SubscriberOutput toDto(T crawlResults);

}
