package com.hyperiongray.sitehound.backend.service.crawler;

import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.htmlunit.UrlFetcher;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static junit.framework.Assert.fail;

/**
 * Created by tomas on 7/26/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("integration-test")
public class UrlFetcherTest{

	@Autowired
	private UrlFetcher urlFetcher;

	@Test
	public void  fetchHttpTest(){
		String fetched=null;
		try{
			fetched=urlFetcher.fetch("http://www.hyperiongray.com//");
			Assert.assertTrue("good size", fetched.length()>1000);
		}catch(IOException e){
			e.printStackTrace();
			fail();
		}catch(InterruptedException e){
			e.printStackTrace();
			fail();
		}
		System.out.println(fetched.substring(0,200));
	}

	@Test
	public void  fetchHttpsTest(){
		String fetched=null;
		try{
			fetched = urlFetcher.fetch("https://schneide.wordpress.com/");
			Assert.assertTrue("good size", fetched.length()>1000);
		}catch(IOException e){
			e.printStackTrace();
			fail();
		}catch(InterruptedException e){
			e.printStackTrace();
			fail();
		}
		System.out.println(fetched.substring(0,200));
	}

	@Test
	public void  fetchHttpsRussianTest(){
		String fetched=null;
		try{
			fetched = urlFetcher.fetch("http://from-ua.com/news/338256-ekspert-esli-ssha-dadut-ukraine-oruzhie-putin-nachnet-voinu.html");
			Assert.assertTrue("good size", fetched.length()>1000);
		}catch(IOException e){
			e.printStackTrace();
			fail();
		}catch(InterruptedException e){
			e.printStackTrace();
			fail();
		}
		System.out.println(fetched.substring(0,200));
	}




	@Test
	@Ignore
	public void  fetchHttpGoogleTest(){
		String fetched=null;
		try{
			fetched=urlFetcher.fetch("http://www.google.com/");
			Assert.assertTrue("good size", fetched.length()>1000);
		}catch(IOException e){
			e.printStackTrace();
			fail();
		}catch(InterruptedException e){
			e.printStackTrace();
			fail();
		}
		System.out.println(fetched.substring(0,200));
	}

	@Test
	@Ignore
	public void  fetchHttpsGoogleTest(){
		String fetched=null;
		try{
			fetched=urlFetcher.fetch("https://www.google.com/");
			Assert.assertTrue("good size", fetched.length()>1000);
		}catch(IOException e){
			e.printStackTrace();
			fail();
		}catch(InterruptedException e){
			e.printStackTrace();
			fail();
		}
		System.out.println(fetched);
	}
}
