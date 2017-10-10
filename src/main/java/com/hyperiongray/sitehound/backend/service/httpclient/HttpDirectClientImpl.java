package com.hyperiongray.sitehound.backend.service.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tomas on 7/20/15.
 */
@Component("httpDirectClient")
public class HttpDirectClientImpl implements HttpClient{


	private CloseableHttpClient httpclient;

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpDirectClientImpl.class);

	@PostConstruct
	public void init() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("google.com", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);

		this.httpclient = HttpClients.custom()
				.setConnectionManager(cm)
				.build();
	}

	@Override
	public org.apache.http.client.HttpClient getClient(){
		return httpclient;
	}

	public String fetch(URI uri) throws IOException {
		Content returnContent = Request.Get(uri).execute().returnContent();
		String content = IOUtils.toString(returnContent.asStream(), Charset.forName("UTF-8"));
		return content;
	}

	public String fetch_ori(URI uri) throws IOException {

		String content = null;
		HttpGet httpget = new HttpGet(uri);
		CloseableHttpResponse response = this.httpclient.execute(httpget);

		try {
			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					content = IOUtils.toString(instream, Charset.forName("UTF-8"));
					LOGGER.debug(content);
//					Document document = Jsoup.parse(instream, "UTF-8", uri.toURL().toJson());
//					content = document.html();
				} finally {
					instream.close();
				}
			}
		} finally {
			response.close();
		}
		return content;
	}


	public static void main2(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
		URI uri = new URIBuilder()
				.setScheme("https")
				.setHost("www.google.com")
				.setPath("/search")
				.setParameter("q", "httpclient")
				.setParameter("btnG", "Google Search")
				.setParameter("aq", "f")
				.setParameter("oq", "")
				.build();

		HttpDirectClientImpl httpDirectClientImpl = new HttpDirectClientImpl();
		httpDirectClientImpl.init();
		String content;
		int i = 0;
		System.out.println("direct");
		while(i<1){
			content = httpDirectClientImpl.fetch(uri);
			System.out.println(content.substring(0,100));
			i++;
		}
	}
}
