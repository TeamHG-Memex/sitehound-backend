package com.hyperiongray.sitehound.backend.test.service.crawler.searchengine.google;

import com.google.common.collect.Lists;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by tomas on 7/9/15.
 */
@Ignore
public class GoogleSearchCrawlerGroupCreatorTest {

	@Test
	public void groupCreator1Test(){
		List<String> included = Lists.newArrayList("word1");
		String actual = CrawlerUtils.groupCreator(included);
		String expected = "word1";
		Assert.assertEquals("different", actual, expected);
		System.out.println(actual);
	}

	@Test
	public void groupCreatorTest(){
		List<String> included = Lists.newArrayList("word1", "word2", "word3", "word4", "word5", "word6", "word7", "word8",
				"word9");
		String actual = CrawlerUtils.groupCreator(included);
		String expected = "(word1 AND word2 AND word3) OR (word4 AND word5 AND word6) OR (word7 AND word8 AND word9)";
		Assert.assertEquals("different", actual, expected);
		System.out.println(actual);
	}

	@Test
	public void groupCreator10Test(){
		List<String> included = Lists.newArrayList("word1","word2","word3","word4","word5","word6","word7","word8",
				"word9","word10");
		String actual = CrawlerUtils.groupCreator(included);
		String expected = "(word1 AND word2 AND word3 AND word4) OR (word5 AND word6 AND word7 AND word8) OR (word9" +
				" AND word10)";
		Assert.assertEquals("different", expected, actual);
		System.out.println(actual);
	}

	@Test
	public void groupCreator11Test(){
		List<String> included = Lists.newArrayList("word1","word2","word3","word4","word5","word6","word7","word8",
				"word9","word10","word11");
		String actual = CrawlerUtils.groupCreator(included);
		String expected = "(word1 AND word2 AND word3) OR (word4 AND word5 AND word6) OR (word7 AND word8 AND word9)" +
				" OR (word10 AND word11)";
		Assert.assertEquals("different", expected, actual);
		System.out.println(actual);
	}
}
