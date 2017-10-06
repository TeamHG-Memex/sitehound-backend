package com.hyperiongray.sitehound.backend.kafka.consumer;

import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.kafka.consumer.mocks.KeywordCrawlerProducerMock;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static junit.framework.TestCase.fail;

/**
 * Created by tomas on 2/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("integration-test")
public class KeywordsFlowTest{

	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsFlowTest.class);

	@Autowired
	KeywordCrawlerProducerMock keywordsProducerMock;


	@Autowired
	CrawlJobRepository crawlJobRepository;

	@Test
	public void keywordsFlowTest(){

		try{
			SubscriberInput subscriberInput = IntegrationHelper.getSubscriberInput();

			crawlJobRepository.updateJobStatus(subscriberInput.getJobId(), Constants.JobStatus.QUEUED);

			keywordsProducerMock.submit(subscriberInput);
		}catch(IOException e){
			LOGGER.error("FAILED",e);
			fail();
		}
		try{
			System.out.println("sleeping");
			Thread.sleep(60*1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
