package com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing;

import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.AbstractCrawlerBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 9/19/15.
 */
@Service
public class BingCrawlerBrokerService extends AbstractCrawlerBrokerService {

	@Autowired private BingSearchCrawler searchCrawler;

	@Override
	protected Crawler getCrawler(){
		return searchCrawler;
	}

}
