package com.hyperiongray.sitehound.backend.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Created by tomas on 7/20/15.
 */
@Component("httpProxyClient")
public class HttpProxyClientImpl implements HttpClient{

	private CloseableHttpClient proxyHttpclient;

	@Value( "${http.proxyHost}" ) private String proxyHost;
	@Value( "${http.proxyPort}" ) private int proxyPort;
	@Value( "${http.proxyUser}" ) private String proxyUsername;
	@Value( "${http.proxyPassword}" ) private String proxyPassword;

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpProxyClientImpl.class);

	@PostConstruct
	public void init() throws KeyManagementException, NoSuchAlgorithmException {

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("google.com", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);

		SSLContext sslContext = SSLContext.getInstance("SSL");

		// set up a TrustManager that trusts everything
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				System.out.println("getAcceptedIssuers =============");
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs,
			                               String authType) {
				System.out.println("checkClientTrusted =============");
			}

			public void checkServerTrusted(X509Certificate[] certs,
			                               String authType) {
				System.out.println("checkServerTrusted =============");
			}
		} }, new SecureRandom());



		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//		credentialsProvider.setCredentials(new AuthScope("localhost", 8080),
//				new UsernamePasswordCredentials("username", "password"));

		credentialsProvider.setCredentials(new AuthScope(proxyHost, proxyPort),
				new UsernamePasswordCredentials(proxyUsername, proxyPassword));

//		HttpHost proxy = new HttpHost("someproxy", 8080);
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);

		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		this.proxyHttpclient = HttpClients.custom()
				.setRoutePlanner(routePlanner)
				.setSslcontext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
				.setDefaultCredentialsProvider(credentialsProvider)
				.build();
	}

	@Override
	public org.apache.http.client.HttpClient getClient(){
		return proxyHttpclient;
	}


	public String fetch(URI uri) throws IOException {


		String content = null;
		HttpGet httpget = new HttpGet(uri);
		CloseableHttpResponse response = this.proxyHttpclient.execute(httpget);
		try {

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					content = IOUtils.toString(instream, Charset.forName("UTF-8"));
					LOGGER.debug(content);
				} finally {
					instream.close();
				}
			}
		} finally {
			response.close();
		}

		return content;
	}
}
