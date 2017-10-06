package com.hyperiongray.sitehound.backend.service.htmlunit;

import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by tomas on 7/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class UrlFetcherTest {

	@Autowired
	private UrlFetcher urlFetcher;

	@Test
	public void fetchTest()  {
		String url = "http://www.amazon.com/";
		String fetch = null;
		try {
			fetch = urlFetcher.fetch(url);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(fetch);
	}


	@Test
	public void fetchRussianTest()  {
		String url = "http://lib.ru/";
		String fetch = null;
		try {
			fetch = urlFetcher.fetch(url);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(fetch);
	}

}
