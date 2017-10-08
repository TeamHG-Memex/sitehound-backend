package com.hyperiongray.sitehound.backend.integration.service.crawler;

import com.hyperiongray.sitehound.backend.integration.IntegrationTestConfiguration;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.SearchEngineCrawlResult;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingSearchParser;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by tomas on 7/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@ActiveProfiles("integration-test")
public class BingSearchHttpCrawlerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BingSearchHttpCrawlerTest.class);

//	@Autowired private BingSearchCrawler bingSearchCrawler;
	@Autowired private BingSearchParser bingSearchParser;
	@Autowired private HttpClientConnector httpClientConnector;

	@Test
	public void fetchTest()  {

		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("http")
					.setHost("www.bing.com")
					.setPath("/search")
					.setParameter("q", "credit cards")
					.build();
		} catch (URISyntaxException e) {
			System.out.println(e);
			fail();
		}

		String content = httpClientConnector.fetch(uri);

		List<SearchEngineCrawlResult> crawlResults = bingSearchParser.parse(content);

		for (SearchEngineCrawlResult result: crawlResults) {
			LOGGER.debug(result.toString());
		}
//		Assert.assertTrue(crawlResults.size()==10);
	}


}
