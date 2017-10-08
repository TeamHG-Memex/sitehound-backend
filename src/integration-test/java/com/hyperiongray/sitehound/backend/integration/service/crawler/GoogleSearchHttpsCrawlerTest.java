package com.hyperiongray.sitehound.backend.integration.service.crawler;

import com.hyperiongray.sitehound.backend.integration.IntegrationTestConfiguration;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.SearchEngineCrawlResult;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleSearchParser;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import junit.framework.Assert;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class GoogleSearchHttpsCrawlerTest {

	@Autowired private HttpClientConnector httpClientConnector;

	@Autowired private GoogleSearchParser googleSearchParser;

	@Test
	public void fetchTest()  {

		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost("www.google.com")
					.setPath("/search")
					.setParameter("q", "credit cards")
					.build();
		} catch (URISyntaxException e) {
			System.out.println(e);
			fail();
		}

		String content = httpClientConnector.fetch(uri);
		List<SearchEngineCrawlResult> googleCrawlResults = googleSearchParser.parse(content);

		for (SearchEngineCrawlResult result: googleCrawlResults) {
			System.out.println(result);
		}
		Assert.assertTrue(googleCrawlResults.size()>=9);
	}


}
