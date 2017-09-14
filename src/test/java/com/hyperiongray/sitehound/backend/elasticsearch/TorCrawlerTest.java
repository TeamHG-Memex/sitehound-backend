package com.hyperiongray.sitehound.backend.elasticsearch;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.crawler.tor.TorCrawler;
import com.hyperiongray.sitehound.backend.service.crawler.tor.TorCrawlerResult;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 7/28/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class TorCrawlerTest{


	@Autowired
	private TorCrawler torCrawler;

	private static final Logger LOGGER = LoggerFactory.getLogger(TorCrawlerTest.class);

	@Test
	public void crawlTest() throws IOException, URISyntaxException{

		List<String> included = Lists.newArrayList("guns","ammo");
		List<String> excluded = Lists.newArrayList();
		Set<String> irrelevantUrls = Sets.newHashSet("www.elpais.es", "www.marca.com");
		Set<String> existentUrl = Sets.newHashSet("www.elpais.es", "www.marca.com");

		List<? extends CrawlResult> crawlResults = torCrawler.crawl(included, excluded, irrelevantUrls,
				                                                  existentUrl, 0, 10);

		int c = 0;
		Set<String> urls = new HashSet<>();
		Set<String> domains = new HashSet<>();
		for( CrawlResult result : crawlResults){
			TorCrawlerResult torCrawlerResult = (TorCrawlerResult) result;
			urls.add(torCrawlerResult.getUrl());
			domains.add(torCrawlerResult.getDomain());
		}

	}
	@Test
	public void crawlPaginatedTest() throws IOException, URISyntaxException{

		List<String> included = Lists.newArrayList("guns","ammo");
		List<String> excluded = Lists.newArrayList();
		Set<String> irrelevantUrls = Sets.newHashSet("www.elpais.es", "www.marca.com");
		Set<String> existentUrl = Sets.newHashSet("www.elpais.es", "www.marca.com");

		List<TorCrawlerResult> crawlResults =null;

		int batchSize = 2;
		int requestedResults = 11;
		int remainingRequestedResults = requestedResults;
		int startingFrom = 0;
		int toRequest;

		while(remainingRequestedResults >0){
			toRequest = remainingRequestedResults < batchSize ? remainingRequestedResults : batchSize;

			LOGGER.info("requesting startingFrom:" + startingFrom + ", toRequest:" + toRequest +" from requestedResults:"+ requestedResults);
			crawlResults=torCrawler.crawl(included, excluded, irrelevantUrls, existentUrl, startingFrom, toRequest);
			if(crawlResults.size()==0){
				break;
			}
			display(crawlResults);
			remainingRequestedResults -= toRequest;
			startingFrom += toRequest;
		}


	}

	private void display(List<TorCrawlerResult> crawlResults){
		int c = 0;
		Set<String> urls = new HashSet<>();
		Set<String> domains = new HashSet<>();
		for( CrawlResult result : crawlResults){
			TorCrawlerResult torCrawlerResult = (TorCrawlerResult) result;
			urls.add(torCrawlerResult.getUrl());
			domains.add(torCrawlerResult.getDomain());
		}
	}
}
