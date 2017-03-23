package com.hyperiongray.sitehound.backend.service.crawler.searchengine.google;

import com.hyperiongray.sitehound.backend.service.crawler.searchengine.AbstractCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 9/19/15.
 */
@Service
public class GoogleCrawlerBrokerService extends AbstractCrawlerBrokerService {

	@Autowired
	private GoogleSearchCrawler googleSearchCrawler;

	@Override
	protected Crawler getCrawler(){
		return googleSearchCrawler;
	}

}
