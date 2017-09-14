package com.hyperiongray.sitehound.backend.test.service.nlp.scorer.cosinesimilarity;

import com.beust.jcommander.internal.Lists;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity.IndexerDao;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity.RelevanceType;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.fail;

/**
 * Created by tomas on 11/29/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class IndexerDaoTest{

	@Autowired private IndexerDao indexerDao;
	private String workspace = "testIndexDao";

	@Before
	public void init(){
	}

	@Test
	public void testIndexOnTheFly() throws Exception{

	}

	@Test
	public void testGetDocVectorByDocId() throws Exception{

	}

	@Test
	public void testBulkIndex(){
		String jobId=getJobId();
		TrainedCrawledUrl irrelevantGoogle=IndexDaoTestHelper.getIrrelevantGoogle();
		TrainedCrawledUrl relevantVolleyMeetup_a=IndexDaoTestHelper.getRelevantVolleyMeetup_A();
		TrainedCrawledUrl relevantVolleyMeetup_b=IndexDaoTestHelper.getRelevantVolleyMeetup_B();
		List<TrainedCrawledUrl> trainedCrawledUrls =Lists.newArrayList(irrelevantGoogle, relevantVolleyMeetup_a, relevantVolleyMeetup_b);
		try {
			indexerDao.bulkIndex(workspace, jobId, RelevanceType.ALL, trainedCrawledUrls);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@NotNull
	private String getJobId(){
		return "jobId_" + Calendar.getInstance().getTimeInMillis();
	}

	@Test
	public void testCreateDocument() throws Exception{

	}

	@Test
	public void testGetDocumentVectors() throws Exception{

	}

	@Test
	public void testGetAllTerms() throws Exception{
		String jobId=getJobId();
		TrainedCrawledUrl irrelevantGoogle=IndexDaoTestHelper.getIrrelevantGoogle();
		TrainedCrawledUrl relevantVolleyMeetup_a=IndexDaoTestHelper.getRelevantVolleyMeetup_A();
		TrainedCrawledUrl relevantVolleyMeetup_b=IndexDaoTestHelper.getRelevantVolleyMeetup_B();
		List<TrainedCrawledUrl> trainedCrawledUrls =Lists.newArrayList(irrelevantGoogle, relevantVolleyMeetup_a, relevantVolleyMeetup_b);
		indexerDao.bulkIndex(workspace, jobId, RelevanceType.ALL, trainedCrawledUrls);
		Map<String, Integer> allTerms=indexerDao.getAllTerms(workspace, jobId);
		System.out.println(allTerms);
	}

	@Test
	public void testIndexOnTheFly1() throws Exception {

	}

	@Test
	public void testGetDocVectorByDocId1() throws Exception {

	}

	@Test
	public void testBulkIndex1() throws Exception {

	}

	@Test
	public void testCreateDocument1() throws Exception {

	}

	@Test
	public void testGetDocumentVectors1() throws Exception {

	}

	@Test
	public void testGetAllTerms1() throws Exception {

	}
}