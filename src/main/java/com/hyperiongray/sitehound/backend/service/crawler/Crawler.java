package com.hyperiongray.sitehound.backend.service.crawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 7/7/15.
 */
public interface Crawler<T extends CrawlResult> {

	//TODO externalize this!
	int MAX_NUMBER_OF_WORDS = 20;

	List<T> crawl(List<String> included, List<String> excluded, Set<String>
			irrelevantHosts, Set<String> existentUrl, int startingFrom, int pageSize) throws URISyntaxException, IOException;


	Constants.CrawlEntityType getCrawlerEntityType();

	int getMaxPageSize();
}
