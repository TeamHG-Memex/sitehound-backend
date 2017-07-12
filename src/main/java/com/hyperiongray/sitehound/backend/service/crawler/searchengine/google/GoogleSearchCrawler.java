package com.hyperiongray.sitehound.backend.service.crawler.searchengine.google;
/**
 * Created by tomas on 5/19/15.
 */

import com.hyperiongray.sitehound.backend.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.SearchEngineCrawlResult;
import org.apache.http.client.utils.URIBuilder;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

@Service
public class GoogleSearchCrawler implements Crawler<SearchEngineCrawlResult> {

	@Autowired
	private HttpClientConnector httpClientConnector;

	@Autowired private GoogleSearchParser googleSearchParser;

//	private static final String GOOGLE_SEARCH_BASE_URL = "http://www.google.com/search?q=";

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchCrawler.class);

	public List<SearchEngineCrawlResult> crawl(List<String> included, List<String> excluded, Set<String>
			irrelevantHosts, Set<String> existentUrl, int startingFrom, int pageSize) throws URISyntaxException {

        if(included.size()==0){
            throw new RuntimeException("included words is empty!");
        }

//		String searchUrl = buildURL(included, excluded, irrelevantHosts, existentUrl,startingFrom, pageSize);
//
		URI uri = buildURL(included, excluded, irrelevantHosts, existentUrl, startingFrom, pageSize);

		//fetch
		String content = httpClientConnector.fetch(uri);

		//parse
		List<SearchEngineCrawlResult> dataFromGoogle = googleSearchParser.parse(content);

		return dataFromGoogle;
	}

	@Override
	public Constants.CrawlEntityType getCrawlerEntityType(){
		return Constants.CrawlEntityType.GOOGLE;
	}

	@Override
	public int getMaxPageSize(){
		return 100;
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
//				.setScheme("https")
				.setScheme("http")
				.setHost("www.google.com")
				.setPath("/search")
				.setParameter("q", sb.toString())
//				.setParameter("btnG", "Google Search")
//				.setParameter("aq", "f")
				.setCharset(Charset.forName("UTF-8"))
//				sb.append("&num=" + pageSize);
				.setParameter("num", String.valueOf(pageSize));

//			sb.append(startingFrom > 0 ? "&getDdTrainerInputStart=" + startingFrom : "");
			if(startingFrom > 0){
				uriBuilder.setParameter("start", String.valueOf(startingFrom));
			}

			uri = uriBuilder.build();

		} catch (URISyntaxException e) {
			LOGGER.error("FAILED TO CRAWL",e);
			throw  new RuntimeException(e);
		}
		LOGGER.info(uri.toString());
		return uri;
	}





	/**
	 * Created by tomas on 7/8/15.
	 */
}