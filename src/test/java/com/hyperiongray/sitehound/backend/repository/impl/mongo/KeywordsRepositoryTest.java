package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.KeywordsRepository;
import junit.framework.TestCase;
import com.hyperiongray.sitehound.backend.config.Configuration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by tomas on 11/18/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class KeywordsRepositoryTest extends TestCase{
	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsRepositoryTest.class);

	@Autowired private KeywordsRepository keywordsRepository;

	@Test
	public void getTrainedDocuments() throws Exception {
		String workspaceId= "57ea86a9d11ff300054a3519";
		List<TrainedCrawledUrl> trainedDocuments = keywordsRepository.getTrainedDocuments(workspaceId);
		System.out.println("size: " + trainedDocuments.size());
		Assert.assertEquals(17, trainedDocuments.size() );

	}

	@Test
	public void getAllUrls() throws Exception {
		String workspaceId= "57ea86a9d11ff300054a3519";
		List<String> trainedDocuments = keywordsRepository.getAllUrls(workspaceId);
		System.out.println("size: " + trainedDocuments.size());
		Assert.assertEquals(185, trainedDocuments.size() );
	}

	@Test
	public void getRelevantTrainedUrls() throws Exception {
		String workspaceId= "57ea86a9d11ff300054a3519";
		List<String> trainedDocuments = keywordsRepository.getRelevantTrainedUrls(workspaceId);
		System.out.println("size: " + trainedDocuments.size());
		Assert.assertEquals(15, trainedDocuments.size() );
	}

	//{"workspaceId":"57ea86a9d11ff300054a3519", "deleted": {"$exists":false}, "pined":true}
	@Test
	@Ignore
	public void getPinnedUrls() throws Exception {
	}



}
