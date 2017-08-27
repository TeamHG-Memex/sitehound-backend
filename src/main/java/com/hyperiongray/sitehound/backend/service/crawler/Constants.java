package com.hyperiongray.sitehound.backend.service.crawler;

/**
 * Created by tomas on 7/8/15.
 */
public class Constants {

	/**
	 * 	the app that performed the crawling
	 */

	public enum CrawlerProvider{
		HH_JOOGLE
	}

	/**
	 *	Which was the source of the data
	 */
	public enum CrawlEntityType {
		GOOGLE, TWITTER, TOR, BING, MANUAL, DD
	}

	/**
	 *	Which type of crawl
	 */
	public enum CrawlType {
		KEYWORDS, BROADCRAWL, DEEPCRAWL
	}

	public enum CrawlerResult{
		SUCCESS, FAIL
	}

	public enum JobStatus{
		STARTED, QUEUED, STOPPED, FINISHED
	}


}
