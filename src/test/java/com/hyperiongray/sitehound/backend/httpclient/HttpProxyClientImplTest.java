package com.hyperiongray.sitehound.backend.httpclient;

import com.hyperiongray.sitehound.backend.Configuration;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tomas on 7/23/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class HttpProxyClientImplTest {

	@Autowired
	private HttpProxyClientImpl instance;

	@Test
	public void fetchTest() throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {

		URI uri = new URIBuilder()
				.setScheme("https")
				.setHost("www.google.com")
				.setPath("/search")
				.setParameter("q", "httpclient")
				.setParameter("btnG", "Google Search")
				.setParameter("aq", "f")
				.setParameter("oq", "")
				.build();

		String content = instance.fetch(uri);
		System.out.println(content);
	}
}
