package com.hyperiongray.sitehound.backend.kafka.dispatcher;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.submitter.TaskSubmitter;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

/**
 * Created by tomas on 10/29/15.
 */
public interface CrawlerBrokerService{

	void process(SubscriberInput subscriberInput, TaskSubmitter taskSubmitter, Constants.CrawlType crawlType);

}
