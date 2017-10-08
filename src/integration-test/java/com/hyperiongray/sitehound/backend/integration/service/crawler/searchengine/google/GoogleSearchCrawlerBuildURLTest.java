package com.hyperiongray.sitehound.backend.integration.service.crawler.searchengine.google;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleSearchCrawler;
import junit.framework.Assert;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 6/30/15.
 */
@Ignore
@ActiveProfiles("integration-test")
public class GoogleSearchCrawlerBuildURLTest {


	@Test
	public void GoogleSearchCrawlerBuildURLTest2() {

		GoogleSearchCrawler instance = new GoogleSearchCrawler();
		List<String> included = Lists.newArrayList("krimalistor@yahoo.de");
		List<String> excluded = Lists.newArrayList();
		Set<String> existentUrl = Sets.newHashSet();
		List<String> irrelevantUrls = Lists.newArrayList();
		Set<String> hosts = CrawlerUtils.getHostsFromUrls(irrelevantUrls);
		URI urlActual = instance.buildURL(included, excluded, hosts, existentUrl, 0, 20);
		String urlExpected = "http://www.google.com/search?q=krimalistor%40yahoo.de&num=20";
		Assert.assertEquals(urlExpected, urlActual.toString());

	}
	@Test
	public void GoogleSearchCrawlerBuildURLTest(){

		GoogleSearchCrawler instance = new GoogleSearchCrawler();
		List<String> included = Lists.newArrayList("barceloneta", "bogatell", "padel surf", "werunbarcelona");
		List<String> excluded = Lists.newArrayList("barza", "fcb", "messi", "futbol");
		List<String> irrelevantUrls = Lists.newArrayList("www.elpais.es", "www.marca.com");
		Set<String> existentUrl = Sets.newHashSet("www.elpais.es", "www.marca.com");

		Set<String> hosts = CrawlerUtils.getHostsFromUrls(irrelevantUrls);

		URI urlActual = instance.buildURL(included, excluded, hosts, existentUrl, 0, 20);
		String urlExpected = "http://www.google.com/search?q=%28barceloneta+AND+bogatell+AND+padel+surf+AND+werunbarcelona%29+-barza+-fcb+-messi+-futbol&num=20";

		Assert.assertEquals(urlExpected, urlActual.toString());
		}

	@Test
	public void GoogleSearchCrawlerBuildURLTestNum20() {
		GoogleSearchCrawler instance = new GoogleSearchCrawler();
		List<String> included = Lists.newArrayList("barceloneta", "bogatell", "padel surf", "werunbarcelona");
		List<String> excluded = Lists.newArrayList("barza", "fcb", "messi", "futbol");
		List<String> irrelevantUrls = Lists.newArrayList("www.elpais.es", "www.marca.com");
		Set<String> existentUrl = Sets.newHashSet("www.elpais.es", "www.marca.com");

		Set<String> hosts = CrawlerUtils.getHostsFromUrls(irrelevantUrls);
		URI urlActual2 = instance.buildURL(included, excluded, hosts, existentUrl, 10, 20);
		String urlExpected2 = "http://www.google" +
				".com/search?q=%28barceloneta+AND+bogatell+AND+padel+surf+AND+werunbarcelona%29+-barza+-fcb+-messi+-futbol&num=20&start=10";
		Assert.assertEquals(urlExpected2, urlActual2.toString());
	}
}
