package com.hyperiongray.sitehound.backend.service.crawler.searchengine;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;

public class SearchEngineCrawlResult extends CrawlResult{

		protected String desc;
		protected String host;
		protected String urlDesc;

		public SearchEngineCrawlResult(Constants.CrawlEntityType crawlEntityType){
			super(crawlEntityType);
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
			return "SearchEngineCrawlResult{" +
					       "desc='" + desc + '\'' +
					       ", host='" + host + '\'' +
					       ", url='" + url + '\'' +
					       ", urlDesc='" + urlDesc + '\'' +
					       ", crawlEntityType=" + getCrawlEntityType() +
					       '}';
		}
	}
