package com.hyperiongray.sitehound.backend.service.crawler.tor;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;

import java.util.List;

/**
 * Created by tomas on 7/27/15.
 */

public class TorCrawlerResult extends CrawlResult{


	//	{"domain": "nucleuspf3izq7o6.onion", "language": "en", "links": [{"link_name": "", "link": "/settings/stealth-mode/on"}, {"link_name": "0", "link": "/my-account/balance"}

	private String domain;
	private String language;
	private List<Link> links;
	private String timestamp;
	private String title;
	private String text;

	private String h1;
	private String h2;
//	private String header;  //cannot be deserialized
	private String Age;
	private String html;

	public TorCrawlerResult(){
		super(Constants.CrawlEntityType.TOR);
	}


	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
		this.domain=domain;
	}

	public String getLanguage(){
		return language;
	}

	public void setLanguage(String language){
		this.language=language;
	}

	public List<Link> getLinks(){
		return links;
	}

	public void setLinks(List<Link> links){
		this.links=links;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(String timestamp){
		this.timestamp=timestamp;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text=text;
	}

	public String getH1(){
		return h1;
	}

	public void setH1(String h1){
		this.h1=h1;
	}

	public String getH2(){
		return h2;
	}

	public void setH2(String h2){
		this.h2=h2;
	}
//
//	public String getHeader(){
//		return header;
//	}
//
//	public void setHeader(String header){
//		this.header=header;
//	}

	public String getAge(){
		return Age;
	}

	public void setAge(String age){
		Age=age;
	}

	public String getHtml(){
		return html;
	}

	public void setHtml(String html){
		// not need to!
//		this.html=html;
	}

	protected class Link{

		String link_name;
		String link;

		public String getLink_name(){
			return link_name;
		}

		public void setLink_name(String link_name){
			this.link_name=link_name;
		}

		public String getLink(){
			return link;
		}

		public void setLink(String link){
			this.link=link;
		}

		@Override
		public String toString(){
			return "Link{" +
					       "link_name='" + link_name + '\'' +
					       ", link='" + link + '\'' +
					       '}';
		}
	}


	@Override
	public String toString(){
		return "TorCrawlerResult{" +
				       "crawlEntityType=" + getCrawlEntityType() +
				       ", domain='" + domain + '\'' +
				       ", language='" + language + '\'' +
				       ", links=" + links +
				       ", timestamp='" + timestamp + '\'' +
				       ", title='" + title + '\'' +
//				       ", text='" + text + '\'' +
				       ", url='" + url + '\'' +
				       ", h1='" + h1 + '\'' +
				       ", h2='" + h2 + '\'' +
//				       ", header='" + header + '\'' +
				       ", Age='" + Age + '\'' +
//				       ", html='" + html + '\'' +
				       '}';
	}
}
