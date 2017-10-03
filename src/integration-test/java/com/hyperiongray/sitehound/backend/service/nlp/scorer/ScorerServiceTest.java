package com.hyperiongray.sitehound.backend.service.nlp.scorer;

import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.service.crawler.CrawledIndexService;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity.IndexerDao;
import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity.RelevanceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by tomas on 17/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, Configuration.class})
public class ScorerServiceTest {

	@Autowired private ScorerService scorerService;
	@Autowired private IndexerDao indexerDao;
	@Autowired private CrawledIndexService mockCrawledIndexService;

	@Autowired
	@Qualifier("alquds")
	private String alqudsHtml;

	@Autowired
	@Qualifier("broadcastschedule")
	private String broadcastschedule;

	@Autowired
	@Qualifier("books")
	private String booksHtml;

	private static final String workspaceId = "56eac53b166f1c72c04be744";


	@Before
	public void before() throws IOException {
		MockitoAnnotations.initMocks(this); //This is a key
		ReflectionTestUtils.setField(scorerService, "crawledIndexService", mockCrawledIndexService);
	}

	@Test
	public void testIndexTrainedData() {
		try{
			String jobId = "job1234" + System.currentTimeMillis() +"test";
			scorerService.indexTrainedData(workspaceId, jobId);
		}
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}

	@Test
	public void testScoreAlquds(){
		try {
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreAlquds";
			scorerService.indexTrainedData(workspaceId, jobId);
			double score = scorerService.score(workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml);
			Assert.isTrue(Math.abs(53.66727353490517f - score) < 1);
			System.out.println(score);
		}
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}

	@Test
	public void testScoreBroadcastschedule(){
		try{
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBroadcastSchedule";
			scorerService.indexTrainedData(workspaceId, jobId);
			double score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule);
			Assert.isTrue(Math.abs(93.30090016978284f - score) <1);
			System.out.println(score);
		}
		catch (Exception e) {
			System.out.println(e);
			fail();
		}
	}

	@Test
	public void testScoreBooks(){
		try {
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooks";
			scorerService.indexTrainedData(workspaceId, jobId);
			double score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml);

			Assert.isTrue(100.0f== score);
			System.out.println(score);
		}
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}


	@Test
	@Repeat(4)
	public void testScoreAll(){
		try {
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooks";
			scorerService.indexTrainedData(workspaceId, jobId);
			double score;
			score = scorerService.score(workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule);
			System.out.println(score);
			score = scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml);
			System.out.println(score);
		}
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}

	@Test
	public void testConcurrentScoreAll(){
		ExecutorService executorService = Executors.newFixedThreadPool(50);
		String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooks";

		try {
			scorerService.indexTrainedData(workspaceId, jobId);
			long start = System.currentTimeMillis();
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/broadcastschedule", broadcastschedule));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));
			executorService.submit(new ScoreServiceTask(scorerService, workspaceId, jobId, "http://www.alquds.co.uk/?p=500864", alqudsHtml));

			executorService.shutdown();
			executorService.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println("Total Time: " + (System.currentTimeMillis() - start));
		}
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}

	public static class ScoreServiceTask implements Runnable{

		private final ScorerService scorerService;
		private final String workspace;
		private final String jobId;
		private final String url;
		private final String text;

		public ScoreServiceTask(ScorerService scorerService, String workspace, String jobId, String url, String text) {
			this.scorerService = scorerService;
			this.workspace = workspace;
			this.jobId = jobId;
			this.url = url;
			this.text = text;
		}

		@Override
		public void run() {
			Double score = null;
			try {
				score = scorerService.score(workspace, jobId, url, text);
				System.out.println(score);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}




	@Test(expected=IOException.class)
	public void testScoreNoIndex() throws IOException {
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooksNoIndex";
			scorerService.score(workspaceId, jobId, "http://www.aljazeera.net/knowledgegate/books", booksHtml);
	}

	@Test
	public void testScoreNullContent(){
		try {
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooksNullContent";
			scorerService.indexTrainedData(workspaceId, jobId);
			double score = scorerService.score(workspaceId, jobId, "http://www.null.com", null);
			Assert.isTrue(0f== score);
		}
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}

	@Test
	public void testScoreNoContent(){
		try{
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooksNoContent";
            scorerService.indexTrainedData(workspaceId, jobId);
            double score = scorerService.score(workspaceId, jobId, "http://www.empty.com", "");
			Assert.isTrue(0f== score);        }
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}


	@Test
	public void testScorePoorContent(){
		try{
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooksPoorContent";
            scorerService.indexTrainedData(workspaceId, jobId);
            double score = scorerService.score(workspaceId, jobId, "http://www.poor.com", " ");
			Assert.isTrue(0f== score);
			score = scorerService.score(workspaceId, jobId, "http://www.poor2.com", "  ");
			Assert.isTrue(0f== score);
        }
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}

	@Test
	public void testScorePoorContent2(){
		try{
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooksPoorContent2";
            scorerService.indexTrainedData(workspaceId, jobId);
			double score;
//			score = scorerService.score(workspaceId, jobId, "http://www.poor3.com", " *");
			score = scorerService.score(workspaceId, jobId, "http://www.poor4.com", "president");
			Assert.isTrue(50f == score);
        }
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}


	@Test
	public void testScoreStopWordsContent(){
		try{
			String jobId = "job1234" + System.currentTimeMillis() + "testScoreBooksStopWordsContent";
//            scorerService.indexTrainedData(workspaceId, jobId);
//			List<TrainedCrawledUrl> trainedDocuments = mockCrawledIndexService.getTrainedDocuments(workspaceId);

			List<TrainedCrawledUrl> trainedDocuments = new LinkedList<>();

			TrainedCrawledUrl trainedCrawledUrl1 = new TrainedCrawledUrl("url1.com", true);
			trainedCrawledUrl1.setText("the lazy fox jumped over the lazy dog");
			trainedDocuments.add(trainedCrawledUrl1);

			TrainedCrawledUrl trainedCrawledUrl2 = new TrainedCrawledUrl("url2.com", true);
			trainedCrawledUrl2.setText("the dog was sleeping");
			trainedDocuments.add(trainedCrawledUrl2);

			indexerDao.bulkIndex(workspaceId,jobId, RelevanceType.ALL, trainedDocuments);
//			indexerDao.bulkIndex(workspaceId,jobId, RelevanceType.RELEVANT, trainedDocuments);
			indexerDao.bulkIndex(workspaceId,jobId, RelevanceType.RELEVANT, new LinkedList<TrainedCrawledUrl>());
			indexerDao.bulkIndex(workspaceId,jobId, RelevanceType.IRRELEVANT, new LinkedList<TrainedCrawledUrl>());

			double score;
//			score = scorerService.score(workspaceId, jobId, "http://www.poor3.com", " *");
			score = scorerService.score(workspaceId, jobId, "http://www.poor5.com", "dog");
			System.out.println("Dog score: " + score);
			score = scorerService.score(workspaceId, jobId, "http://www.poor6.com", "armagedon");
			System.out.println("armagedon score: " + score);
			score = scorerService.score(workspaceId, jobId, "http://www.poor7.com", "a");
			System.out.println("a score: " + score);
		}
		catch (Exception e){
			System.out.println(e);
			fail();
		}
	}


}
