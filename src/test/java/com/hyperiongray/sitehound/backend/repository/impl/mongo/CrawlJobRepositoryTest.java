package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 12/21/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class CrawlJobRepositoryTest{

	@Autowired
	private CrawlJobRepository instance;

	@Test
	public void updateJobStatusTest(){
	 instance.updateJobStatus("56776d7a132ad25bee6fff29", Constants.JobStatus.STARTED);
	}

	@Test
	public void isJobCancelledTestWhenYes(){
		boolean jobActive = instance.isJobActive("56776d7a132ad25bee6fff29");
		System.out.println(jobActive);
	}

	@Test
	public void isJobCancelledTestWhenNot(){
		boolean jobActive = instance.isJobActive("567762ec132ad2515051ee03");
		System.out.println(jobActive);
	}

}
