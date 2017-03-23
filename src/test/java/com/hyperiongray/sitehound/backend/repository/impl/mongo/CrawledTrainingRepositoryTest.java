package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import junit.framework.TestCase;
import com.hyperiongray.sitehound.backend.Configuration;
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
public class CrawledTrainingRepositoryTest extends TestCase{

	@Autowired private CrawledTrainingRepository crawledTrainingRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CrawledTrainingRepositoryTest.class);

	@Test
	public void test(){
		List<TrainedCrawledUrl> trainedCrawledUrls = crawledTrainingRepository.getTrainedDocuments("volley3");

		for(TrainedCrawledUrl trainedCrawledUrl : trainedCrawledUrls){
			LOGGER.debug(trainedCrawledUrl.toString());
		}

	}
}
