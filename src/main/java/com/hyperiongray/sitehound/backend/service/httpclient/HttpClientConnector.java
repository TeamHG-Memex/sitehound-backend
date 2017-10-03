package com.hyperiongray.sitehound.backend.service.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

/**
 * Created by tomas on 7/23/15.
 */
@Service
public class HttpClientConnector {

	@Autowired
	@Qualifier("httpDirectClient")
	private HttpClient httpClient;

	@Autowired
	@Qualifier("httpProxyClient")
	private HttpClient httpProxyClient;

	@Value( "${http.proxy.enabled}" ) private boolean proxyEnabled;

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientConnector.class);


	public org.apache.http.client.HttpClient getClient(){
		if(proxyEnabled){
			return this.httpProxyClient.getClient();
		}
		else{
			return this.httpClient.getClient();
		}
	}

	private String directFetch(URI uri) throws IOException {
		LOGGER.info("Sending direct request: " + uri);
		return httpClient.fetch(uri);
	}

	private String proxyFetch(URI uri) throws IOException {
		LOGGER.info("Sending proxy request: " + uri);
		return httpProxyClient.fetch(uri);
	}


	public String fetch(URI uri){
		String content = null;
		LOGGER.info("proxyEnabled:" + proxyEnabled);
		try {
			if(proxyEnabled){
				content = this.proxyFetch(uri);
			}
			else{
				content = this.directFetch(uri);
			}
			LOGGER.info("Finished requesting URL:" + uri);
		} catch (IOException e) {
			LOGGER.error("error fetching:" + uri);
			throw new RuntimeException("MAYOR ERROR CRAWLING-FETCH");
		}
		return content;
	}


}
