package com.hyperiongray.sitehound.backend.service.crawler.twitter;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 7/7/15.
 */
@Component
public class TwitterSearchCrawler implements Crawler<TwitterCrawlResult>{

	@Value( "${twitter.consumer.key}" ) private String consumerKey;
	@Value( "${twitter.consumer.secret}" ) private String consumerSecret;
	@Value( "${twitter.host}" ) private String twitterHost;
	@Value( "${twitter.status.url}" ) private String twitterStatusUrl;

	private Twitter twitter;

	private static final DateFormat df = new SimpleDateFormat("hh:mm - dd MMM YYYY");
	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterSearchCrawler.class);

	@PostConstruct
	public void postConstruct(){
		//oath
		OAuth2Token token = getOAuth2Token(consumerKey, consumerSecret);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		cb.setOAuth2TokenType(token.getTokenType());
		cb.setOAuth2AccessToken(token.getAccessToken());
		twitter = new TwitterFactory(cb.build()).getInstance();
	}


	@Override
	public Constants.CrawlEntityType getCrawlerEntityType(){
		return Constants.CrawlEntityType.TWITTER;
	}

	@Override
	public int getMaxPageSize(){
		return 10;
	}

	@Override
	public List<TwitterCrawlResult> crawl(List<String> included, List<String> excluded, Set<String> irrelevantHosts,
	                                      Set<String> existentUrl, int startingFrom, int pageSize) {

		String queryString = CrawlerUtils.groupCreator(included);

		if(excluded.size()>0){
			String excludedWords = CrawlerUtils.implodeArray(excluded.toArray(new String[]{}), " -");
			queryString = queryString + " -"+ excludedWords;
		}

		LOGGER.info("twitter query: " + queryString);
		Query query = new Query(queryString);	// Search for tweets that contain these two words
		query.setCount(pageSize);							// Let's get all the tweets we can in one call
		query.resultType(Query.MIXED);						// Get all tweets
//		query.setLang("en");							// English language tweets, please
		query.sinceId(0);
		query.maxId(Long.MAX_VALUE);


		QueryResult queryResult = null;			// Make the call
		try {
			queryResult = twitter.search(query);
		} catch (TwitterException e) {
			LOGGER.error("TwitterException", e);
		}

		List<TwitterCrawlResult> contentSearchResults = new LinkedList<>();


		LOGGER.info("Query", query);

		for (Status status: queryResult.getTweets())				// Loop through all the tweets...
		{
			LOGGER.info("Status", status);
			TwitterCrawlResult contentSearchResult = new TwitterCrawlResult();
			contentSearchResult.setHost(twitterHost);
			contentSearchResult.setUrl(String.format(twitterStatusUrl, status.getUser().getScreenName(), status.getId()));
			contentSearchResult.setDesc(status.getText());
			contentSearchResult.setUrlDesc(
					"@" + status.getUser().getScreenName() + " - "
					+ status.getUser().getName() + " - "
					+ df.format(status.getCreatedAt()));
			contentSearchResults.add(contentSearchResult);
		}
		return contentSearchResults;
	}

	private OAuth2Token getOAuth2Token(String consumerKey, String consumerSecret) {
		OAuth2Token token = null;
		ConfigurationBuilder cb;
		cb = new ConfigurationBuilder();
		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		try {
			token = new TwitterFactory(cb.build()) .getInstance().getOAuth2Token();
		} catch (Exception e) {
			LOGGER.error("Can't get OAuth2 token", e);
		}
		return token;
	}

}
