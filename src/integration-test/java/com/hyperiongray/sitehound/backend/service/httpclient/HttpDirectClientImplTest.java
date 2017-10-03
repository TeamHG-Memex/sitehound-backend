package com.hyperiongray.sitehound.backend.service.httpclient;

import com.hyperiongray.sitehound.backend.TestConfiguration;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by tomas on 7/23/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class HttpDirectClientImplTest {

	@Autowired
	private HttpDirectClientImpl instance;

	@Test
	public void fetchTest() throws URISyntaxException, IOException {

		URI uri = new URIBuilder()
				.setScheme("https")
				.setHost("www.google.com")
				.setPath("/search")
				.setParameter("q", "httpclient")
				.setParameter("btnG", "Google Search")
				.setParameter("aq", "f")
				.setParameter("oq", "")
				.build();

//		instance.init();
		String content;
		System.out.println("proxy");
		content = instance.fetch(uri);
		System.out.println(content);
	}

	@Test
	public void encodingTest() throws URISyntaxException, IOException{

		URI uri = new URIBuilder()
				          .setScheme("http")
				          .setHost("from-ua" +
						                   ".com/news/338256-ekspert-esli-ssha-dadut-ukraine-oruzhie-putin" +
						                   "-nachnet-voinu.html")
				          .build();
		String fetch = instance.fetch(uri);
		System.out.println(fetch);
	}
}
