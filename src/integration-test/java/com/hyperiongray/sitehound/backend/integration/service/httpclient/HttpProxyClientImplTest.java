package com.hyperiongray.sitehound.backend.integration.service.httpclient;

import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.integration.IntegrationTestConfiguration;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
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
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@ActiveProfiles("integration-test")
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
