package com.hyperiongray.sitehound.backend.service.crawler.twitter;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;

/**
 * Created by tomas on 7/8/15.
 */
public class TwitterCrawlResult extends CrawlResult{

	protected String desc;
	protected String host;
	protected String urlDesc;

	public TwitterCrawlResult(){
		super(Constants.CrawlEntityType.TWITTER);
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setUrlDesc(String urlDesc) {
		this.urlDesc = urlDesc;
	}

	public String getUrlDesc() {
		return urlDesc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}


	@Override
	public String toString(){
		return "TwitterCrawlResult{" +
				       "desc='" + desc + '\'' +
				       ", host='" + host + '\'' +
				       ", url='" + url + '\'' +
				       ", urlDesc='" + urlDesc + '\'' +
				       ", crawlEntityType=" + getCrawlEntityType() +
				       '}';
	}
}
