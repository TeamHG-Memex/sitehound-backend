package com.hyperiongray.sitehound.backend.service.httpclient;

import com.hyperiongray.sitehound.backend.TestConfiguration;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by tomas on 8/27/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("integration-test")
public class HttpClientConnectorTest{

	@Autowired
	private HttpClientConnector httpClientConnector;

	@Test
	public void connectorTest() throws URISyntaxException{
		URI uri = new URIBuilder()
				          .setScheme("http")
				          .setHost("from-ua.com/news/338256-ekspert-esli-ssha-dadut-ukraine-oruzhie-putin-nachnet" +
						                   "-voinu.html")
						.build();
		String fetch=httpClientConnector.fetch(uri);
	}

	@Test
	public void connectorGoogleTest() throws URISyntaxException{
		URI uri = new URIBuilder()
				          .setScheme("https")
				          .setHost("www.google.es/search?q=%D0%BF%D0%BE%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D0%BE+%D1%87%D0%B8%D1%81%D0%BB%D0%BE+%D0%BF%D1%80%D0%BE%D1%81%D0%BC%D0%BE%D1%82%D1%80%D0%BE%D0%B2&oq=%D0%BF%D0%BE%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D0%BE+%D1%87%D0%B8%D1%81%D0%BB%D0%BE+%D0%BF%D1%80%D0%BE%D1%81%D0%BC%D0%BE%D1%82%D1%80%D0%BE%D0%B2&aqs=chrome..69i57.870j0j7&sourceid=chrome&es_sm=93&ie=UTF-8")
				          .build();

//		https://www.google.es/search?q=%D0%BF%D0%BE%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D0%BE+%D1%87%D0%B8%D1%81%D0%BB%D0%BE+%D0%BF%D1%80%D0%BE%D1%81%D0%BC%D0%BE%D1%82%D1%80%D0%BE%D0%B2&oq=%D0%BF%D0%BE%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D0%BE+%D1%87%D0%B8%D1%81%D0%BB%D0%BE+%D0%BF%D1%80%D0%BE%D1%81%D0%BC%D0%BE%D1%82%D1%80%D0%BE%D0%B2&aqs=chrome..69i57.870j0j7&sourceid=chrome&es_sm=93&ie=UTF-8

		String fetch=httpClientConnector.fetch(uri);
	}
}
