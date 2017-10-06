package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd;

import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 12/21/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("integration-test")
public class CrawlJobRepositoryTest{

	@Autowired
	private CrawlJobRepository instance;

//	@Test
//	public void get() throws Exception {
//	}
//
//	@Test
//	public void updateJobStatus() throws Exception {
//	}
//
//	@Test
//	public void isJobActive() throws Exception {
//	}
//
//	@Test
//	public void getWorkspaceId() throws Exception {
//	}

	@Test
	public void saveProgress() throws Exception {
		String workspaceId = "59c27d45e91b2e11edbaf859";
		String jobId = "59cb6a00e91b2e3fe857d90e";
		Double percentageDone = 98.123;
		String progress = "Crawled N pages and M domains, average reward is 0.1288882";


		DdCrawlerOutputProgress ddCrawlerOutputProgress = new DdCrawlerOutputProgress();
		ddCrawlerOutputProgress.setWorkspaceId(workspaceId);
		ddCrawlerOutputProgress.setId(jobId);
		ddCrawlerOutputProgress.setProgress(progress);
		ddCrawlerOutputProgress.setPercentageDone(percentageDone);

		instance.saveProgress(ddCrawlerOutputProgress);

		CrawlJob crawlJob = instance.get(jobId);
		System.out.println(crawlJob);
	}


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
