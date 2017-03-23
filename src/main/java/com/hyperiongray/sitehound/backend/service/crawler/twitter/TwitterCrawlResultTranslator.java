package com.hyperiongray.sitehound.backend.service.crawler.twitter;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberOutput;
import com.hyperiongray.sitehound.backend.service.crawler.GenericCrawlResultTranslator;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;

/**
 * Created by tomas on 7/27/15.
 */
public class TwitterCrawlResultTranslator extends GenericCrawlResultTranslator {

	@Override
	public <T extends CrawlResult> SubscriberOutput toDto(T crawlResults){
		return null;
	}
}
