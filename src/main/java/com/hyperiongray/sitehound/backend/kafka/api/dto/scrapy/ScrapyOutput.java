package com.hyperiongray.sitehound.backend.kafka.api.dto.scrapy;

/**
 * Created by tomas on 10/30/15.
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * {
	 "appid": "datawake",
	 "crawlid": "575973a6-8636-42dd-87a9-484242a1",
	 "url": "http://dmoz.org/Business",
	 "response_url": "http://dmoz.org/Business/",
	 "status_code": "200",
	 "status_msg": "Success",
	 "timestamp": "2014-10-04T08:00:000Z",
	 "links": [
		{"url": "http://dmoz.org/Business/", "text": "Business"},
		{"url": "http://dmoz.org/Sports/", "text": "Sports"}
	 ],
	 "headers": {"Content-Type": "..."}
	 "body": "\x01...\x01",
	 "attrs": {
		 "foo": "bar"
	 }
 }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScrapyOutput{

	/**"appid": "datawake" */
	private String appid;

	/** "crawlid": "575973a6-8636-42dd-87a9-484242a1" */
	private String crawlid;

	/**	"url": "http://dmoz.org/Business" */
	private String url;

	/**	"domain": "http://dmoz.org" */
	private String domain;

	/** "response_url": "http://dmoz.org/Business/" */
	private String response_url;

	/** "status_code": "200" */
	private String status_code;

	/** "status_msg": "Success" */
	private String status_msg;

	/** "timestamp": "2014-10-04T08:00:000Z" */
	private String timestamp;

	/**
	"links": [
		{"url": "http://dmoz.org/Business/", "text": "Business"},
		{"url": "http://dmoz.org/Sports/", "text": "Sports"}
	]
	*/
	private List<String> links;

	/** "headers": {"Content-Type": "..."} */
//	private Map<String, String> headers;

	/** "body": "\x01...\x01" */
//	private String body;

	/**
	"attrs": {
		"foo": "bar"
	}
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

	public String getResponse_url(){
		return response_url;
	}

	public void setResponse_url(String response_url){
		this.response_url=response_url;
	}

	public String getStatus_code(){
		return status_code;
	}

	public void setStatus_code(String status_code){
		this.status_code=status_code;
	}

	public String getStatus_msg(){
		return status_msg;
	}

	public void setStatus_msg(String status_msg){
		this.status_msg=status_msg;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(String timestamp){
		this.timestamp=timestamp;
	}

	public List<String> getLinks(){
		return links;
	}

	public void setLinks(List<String> links){
		this.links=links;
	}

//	public Map<String, String> getHeaders(){
//		return headers;
//	}
//
//	public void setHeaders(Map<String, String> headers){
//		this.headers=headers;
//	}

//	public String getBody(){
//		return body;
//	}
//
//	public void setBody(String body){
//		this.body=body;
//	}
//
	public Map<String, String> getAttrs(){
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs){
		this.attrs=attrs;
	}

	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
		this.domain=domain;
	}

	@Override
	public String toString(){
		return "ScrapyOutput{" +
				       "appid='" + appid + '\'' +
				       ", crawlid='" + crawlid + '\'' +
				       ", url='" + url + '\'' +
				       ", domain='" + domain + '\'' +
				       ", response_url='" + response_url + '\'' +
				       ", status_code='" + status_code + '\'' +
				       ", status_msg='" + status_msg + '\'' +
				       ", timestamp='" + timestamp + '\'' +
				       ", links=" + links +
//				       ", headers=" + headers +
				       ", body='" + "..." + '\'' +
				       ", attrs=" + attrs +
				       '}';
	}

}
