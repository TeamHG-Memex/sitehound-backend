package com.hyperiongray.sitehound.backend.service.crawler;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.fail;

/**
 * Created by tomas on 7/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class HttpProxyClientTest {

	@Autowired
	private HttpProxyClientImpl httpProxyClient;


	private String fetchHost(String host) throws IOException {
		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("http")
//					.setHost("www.apache.org")
					.setHost(host)
					.build();
		} catch (URISyntaxException e) {
			System.out.println(e);
			fail();
		}

		String content = httpProxyClient.fetch(uri);
		return content;
	}

	@Test
	public void connectTest() throws IOException, URISyntaxException {
		String host = "www.apache.org";
		System.out.println(fetchHost(host));
	}

	@Test
	public void connectTest2() throws IOException, URISyntaxException {
		String host = "www.hyperiongray.com";
		System.out.println(fetchHost(host));
	}

	@Test
	public void connectTest3() throws IOException, URISyntaxException {
		String host = "www.google.com";
		System.out.println(fetchHost(host));
	}

	@Test
	public void connectSpacesTest() throws IOException, URISyntaxException {
//
//		String initialRequestUrl = "http://www.google.com/search?q=(\"credit cards\" AND sale AND sell) OR (forum AND SSN AND " +
//				"CVV) OR (card AND valid AND credit) OR (selling AND dumps AND cvv) OR (ssn AND fullz AND cards) -news -fraud +-site:twitter.com&num=10&getDdTrainerInputStart=90";

		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("http")
					.setHost("www.google.com")
					.setPath("/search")
					.setParameter("q", "(\"credit cards\" AND sale AND sell) OR (forum AND SSN AND CVV) " +
							"OR (card AND valid AND credit) OR (selling AND dumps AND cvv) OR (ssn AND fullz AND cards)")
					.setParameter("", "-news -fraud +-site:twitter.com")
					.setParameter("num", "10")
					.setParameter("getDdTrainerInputStart", "90")
					.build();
		} catch (URISyntaxException e) {
			System.out.println(e);
			fail();
		}

		System.out.println(uri);
		String content = httpProxyClient.fetch(uri);
		System.out.println(content);
	}

	@Test
	/**
	 * test "https://www.yahoo.com/politics/republicans-defend-mccain-against-attacks-from-124429756316.html"
	 */
	public void connectSpacesSimpleTest() throws IOException, URISyntaxException {
		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost("www.yahoo.com")
					.setPath("/politics/republicans-defend-mccain-against-attacks-from-124429756316.html")
					.build();
		} catch (URISyntaxException e) {
			System.out.println(e);
			fail();
		}
		String content = httpProxyClient.fetch(uri);
		System.out.println(content);
	}
}