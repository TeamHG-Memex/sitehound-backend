package com.hyperiongray.sitehound.backend.service.crawler.tor;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberOutput;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;
import com.hyperiongray.sitehound.backend.service.crawler.GenericCrawlResultTranslator;
import org.springframework.stereotype.Component;

/**
 * Created by tomas on 7/27/15.
 */
@Deprecated
@Component
public class TorCrawlResultTranslator extends GenericCrawlResultTranslator{

	@Override
	public <T extends CrawlResult> SubscriberOutput toDto(T crawlResults){

		TorCrawlerResult torCrawlerResult = (TorCrawlerResult) crawlResults;
		SubscriberOutput subscriberOutput = new SubscriberOutput();

		subscriberOutput.setUrl(torCrawlerResult.getUrl());
		subscriberOutput.setHost(torCrawlerResult.getDomain());
		subscriberOutput.setCrawlEntityType(Constants.CrawlEntityType.TOR);
		subscriberOutput.setUrlDesc(torCrawlerResult.getTitle());
		subscriberOutput.setDesc(torCrawlerResult.getText().length()>100
				                         ? torCrawlerResult.getText().substring(0,100)
				                         : torCrawlerResult.getText());

		return subscriberOutput;
	}
}
