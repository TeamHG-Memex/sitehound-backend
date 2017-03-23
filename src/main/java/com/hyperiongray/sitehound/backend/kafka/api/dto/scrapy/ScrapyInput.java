package com.hyperiongray.sitehound.backend.kafka.api.dto.scrapy;

/**
 * Created by tomas on 10/29/15.
 */

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use Example
 *
 * # topic: incoming_urls
 {
 "appid": "datawake", # REQUIRED: application ID; output written to crawled_{appid}
 "crawlid": "575973a6-8636-42dd-87a9-484242a1", # REQUIRED: crawl UUID for tracking
 "url": "http://dmoz.org/Business", # REQUIRED: URL string
 "spiderid": "link", # spider ID
 "priority": 1, # 1-100, 100 is highest; may be ignored for now
 "maxdepth": 0, # 0-3, crawling depth
 "allowed_domains": ["dmoz.org"], # which domains to follow for links
 "allow_regex": ["dmoz.org"], # which domains to allow (regex matching)
 "deny_regex": ["dmoz.org"], # which domains to deny (regex matching)
 "deny_extensions": ["dmoz.org"],# which extensions to *not* scrape.
 "useragent": "string",# specify useragent to use when scraping. By default it rotates the agent.
 "expires": 1426270829, # integer in UNIX time when the crawl should expire.
 "attrs": { # arbitrary attributes to pass-through crawl
	 "foo": "bar"
    }
 }
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ScrapyInput{

	/** REQUIRED: application ID; output written to crawled_{appid} */
	private String appid;

	/**
	 * # REQUIRED: crawl UUID for tracking
	 * "575973a6-8636-42dd-87a9-484242a1"
	 */
	private String crawlid;

	/**
	 * # REQUIRED: URL string
	 * "http://dmoz.org/Business"
	 */
	private String url;

	/**
	 * # spider ID
	 * "link"
	 */
	private String spiderid;

	/**
	 * # 1-100, 100 is highest; may be ignored for now
	 * 1
	 */
	private int priority;

	/**
	 * # 0-3, crawling depth
	 * 0
	 */
	private int maxdepth;

	/**
	 * # which domains to follow for links
	 * ["dmoz.org"]
	 */
	private List<String> allowed_domains;

	/**
	 * # which domains to allow (regex matching)
	 * ["dmoz.org"]
	 */
	private List<String> allow_regex;

	/**
	 * # which domains to deny (regex matching)
	 * ["dmoz.org"]
	 */
	private List<String> deny_regex;

	/**
	 * # which extensions to *not* scrape.
	 * ["dmoz.org"]
	 */
	private List<String> deny_extensions;

	/**
	 * # specify useragent to use when scraping. By default it rotates the agent.
	 * "string"
	 */
	private String useragent;

	/**
	 * # integer in UNIX time when the crawl should expire.
	 * 1426270829
	 */
	private Long expires;


	/**
	 * # arbitrary attributes to pass-through crawl
	 * {"foo": "bar"}
	 */
	private Map<String, String> attrs;


	public String getAppid(){
		return appid;
	}

	public void setAppid(String appid){
		this.appid=appid;
	}

	public String getCrawlid(){
		return crawlid;
	}

	public void setCrawlid(String crawlid){
		this.crawlid=crawlid;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getSpiderid(){
		return spiderid;
	}

	public void setSpiderid(String spiderid){
		this.spiderid=spiderid;
	}

	public int getPriority(){
		return priority;
	}

	public void setPriority(int priority){
		this.priority=priority;
	}

	public int getMaxdepth(){
		return maxdepth;
	}

	public void setMaxdepth(int maxdepth){
		this.maxdepth=maxdepth;
	}

	public List<String> getAllowed_domains(){
		return allowed_domains;
	}

	public void setAllowed_domains(List<String> allowed_domains){
		this.allowed_domains=allowed_domains;
	}

	public List<String> getAllow_regex(){
		return allow_regex;
	}

	public void setAllow_regex(List<String> allow_regex){
		this.allow_regex=allow_regex;
	}

	public List<String> getDeny_regex(){
		return deny_regex;
	}

	public void setDeny_regex(List<String> deny_regex){
		this.deny_regex=deny_regex;
	}

	public List<String> getDeny_extensions(){
		return deny_extensions;
	}

	public void setDeny_extensions(List<String> deny_extensions){
		this.deny_extensions=deny_extensions;
	}

	public String getUseragent(){
		return useragent;
	}

	public void setUseragent(String useragent){
		this.useragent=useragent;
	}

	public Long getExpires(){
		return expires;
	}

	public void setExpires(Long expires){
		this.expires=expires;
	}

	public Map<String, String> getAttrs(){
		if(attrs == null){
			attrs = new HashMap<>();
		}
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs){
		this.attrs=attrs;
	}

	public void addAttr(String key, String value){
		this.getAttrs().put(key, value);
	}

	@Override
	public boolean equals(Object o){
		if(this==o) return true;
		if(!(o instanceof ScrapyInput)) return false;

		ScrapyInput that=(ScrapyInput) o;

		return url.equals(that.url);

	}

	@Override
	public int hashCode(){
		return url.hashCode();
	}
}
