package com.hyperiongray.sitehound.backend.service.htmlunit;

import com.gargoylesoftware.htmlunit.HttpWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.http.client.HttpClient;

/**
 * Created by tomas on 7/26/15.
 */

public class HttpClientBackedWebConnection extends HttpWebConnection{

	private HttpClient client;

	public HttpClientBackedWebConnection(WebClient webClient,HttpClient client) {
		super(webClient);
		this.client = client;
	}

	protected HttpClient getHttpClient() {
		return client;
	}
}
