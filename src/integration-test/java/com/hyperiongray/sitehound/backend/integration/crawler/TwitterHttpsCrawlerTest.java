package com.hyperiongray.sitehound.backend.integration.crawler;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.service.crawler.twitter.TwitterCrawlResult;
import com.hyperiongray.sitehound.backend.service.crawler.twitter.TwitterSearchCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 7/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class TwitterHttpsCrawlerTest{

	@Autowired
	private TwitterSearchCrawler twitterSearchCrawler;

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterHttpsCrawlerTest.class);


	@Test
	public void crawlTest(){
		List<String> included = Lists.newArrayList("barceloneta");
		List<String> excluded = Lists.newArrayList("barza", "fcb", "messi", "futbol");
		Set<String> irrelevantUrls = Sets.newHashSet("www.elpais.es", "www.marca.com");
		Set<String> existentUrl = Sets.newHashSet("www.elpais.es", "www.marca.com");
		List<TwitterCrawlResult> searchResults = twitterSearchCrawler.crawl(included, excluded, irrelevantUrls,
				existentUrl, 0, 10);
		for(TwitterCrawlResult searchResult: searchResults){
			LOGGER.info(searchResult.toString());
		}

	}

	@Test
	public void crawlTest2(){
		List<String> included = Lists.newArrayList("greece");
		List<String> excluded = Lists.newArrayList();
		Set<String> irrelevantUrls = Sets.newHashSet();
		Set<String> existentUrl = Sets.newHashSet();
		List<TwitterCrawlResult> searchResults = twitterSearchCrawler.crawl(included, excluded, irrelevantUrls,
				existentUrl, 0, 10);
		for(TwitterCrawlResult searchResult: searchResults){
			LOGGER.info(searchResult.toString());
		}

	}

	@Test
	public void crawlTomasTest(){
		List<String> included = Lists.newArrayList("@nredvelvet");
		List<String> excluded = Lists.newArrayList();
		Set<String> irrelevantUrls = Sets.newHashSet();
		Set<String> existentUrl = Sets.newHashSet();
		List<TwitterCrawlResult> searchResults = twitterSearchCrawler.crawl(included, excluded, irrelevantUrls,
				existentUrl, 0, 10);
		for(TwitterCrawlResult searchResult: searchResults){
			LOGGER.info(searchResult.toString());
		}

	}
}