package com.hyperiongray.sitehound.backend.test.service.crawler;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.SearchEngineCrawlResult;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleSearchCrawler;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 6/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class HttpsCrawlerTest {

	@Autowired
	private GoogleSearchCrawler instance;

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsCrawlerTest.class);


	@Test
	public void crawlTest() throws IOException, URISyntaxException {


		SubscriberInput subscriberInput = new SubscriberInput();
		subscriberInput.setIncluded(Lists.newArrayList("mario"));
		subscriberInput.setExcluded(Lists.newArrayList("maria"));
		Set<String> hosts = Sets.newHashSet("www.elpais.es", "www.marca.com");
		Set<String> existentUrl = Sets.newHashSet("www.amazon.com");

		List<SearchEngineCrawlResult> result = instance.crawl(subscriberInput.getIncluded(), subscriberInput
				.getExcluded(), existentUrl, hosts, 200, 0);
		for(SearchEngineCrawlResult temp : result){
			LOGGER.info("result", temp);
		}
		LOGGER.info("size", result.size());
	}
}
