package com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing;
/**
 * Created by tomas on 7/8/15.
 */

import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import org.apache.http.client.utils.URIBuilder;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.SearchEngineCrawlResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@Service
public class BingSearchCrawler implements Crawler<SearchEngineCrawlResult> {

	@Autowired private HttpClientConnector httpClientConnector;
	@Autowired private BingSearchParser bingSearchParser;


	private static final Logger LOGGER = LoggerFactory.getLogger(BingSearchCrawler.class);

	public List<SearchEngineCrawlResult> crawl(List<String> included, List<String> excluded, Set<String>
			irrelevantHosts, Set<String> existentUrl, int startingFrom, int pageSize) throws URISyntaxException {

        if(included.size()==0){
            throw new RuntimeException("included-words is empty!");
        }

		URI uri = buildURL(included, excluded, irrelevantHosts, existentUrl, startingFrom, pageSize);

		//fetch
		String content = httpClientConnector.fetch(uri);

		//parse
		List<SearchEngineCrawlResult> dataFromGoogle = bingSearchParser.parse(content);

		return dataFromGoogle;
	}

	@Override
	public Constants.CrawlEntityType getCrawlerEntityType(){
		return Constants.CrawlEntityType.BING;
	}

	@Override
	public int getMaxPageSize(){
		return 50;
	}


	public URI buildURL(List<String> included, List<String> excluded, Set<String> irrelevantHosts,
	                        Set<String> existentUrl,
	                        int startingFrom, int pageSize){

		URI uri;
		StringBuilder sb = new StringBuilder();
		sb.append(CrawlerUtils.groupCreator(included));
		if(excluded.size()>0){
			sb.append(" -" + CrawlerUtils.implodeArray(excluded.toArray(new String[]{}), " -"));
		}

		try {
			URIBuilder uriBuilder = new URIBuilder()
				.setScheme("http")
				.setHost("www.bing.com")
				.setPath("/search")
				.setParameter("q", sb.toString())
				.setParameter("count", String.valueOf(pageSize));

			if(startingFrom > 0){
				uriBuilder.setParameter("first", String.valueOf(startingFrom));
			}

			uri = uriBuilder.build();

		} catch (URISyntaxException e) {
			LOGGER.error("FAILED TO CRAWL",e);
			throw  new RuntimeException(e);
		}
		LOGGER.info(uri.toString());

		return uri;
	}

}