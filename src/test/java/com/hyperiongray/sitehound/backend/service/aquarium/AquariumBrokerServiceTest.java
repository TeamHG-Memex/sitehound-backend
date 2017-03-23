package com.hyperiongray.sitehound.backend.service.aquarium;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.jetbrains.annotations.NotNull;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tomas on 2/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class AquariumBrokerServiceTest{

	@Autowired AquariumBrokerService instance;
//	@Autowired
//	KeywordsAquariumCallbackServiceBaseAquariumCallbackService aquariumCallbackService;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumBrokerServiceTest.class);
//
//	@Test
//	public void testBroadcrawlFetch() throws Exception{
//
//		AquariumCallbackServiceMockUtils.mockScorerService(aquariumCallbackService);
//		Semaphore semaphore = new Semaphore(1);
//		semaphore.acquire(1);
//
//		Metadata metadata = getMetadata_broadcrawl();
//
//		AquariumInput aquariumInput = new AquariumInput(metadata);
//		aquariumInput.setUrl("https://www.elastic.co/guide/en/elasticsearch/guide/current/partial-updates.html");
//
//		instance.fetch(semaphore, aquariumInput);
//		semaphore.acquire(1);
//	}

//	@Test
//	public void testKeywordsFetch() throws Exception{
//
//		AquariumCallbackServiceMockUtils.mockScorerService(aquariumCallbackService);
//		Semaphore semaphore = new Semaphore(1);
//		semaphore.acquire(1);
//
//		Metadata metadata = getMetadata_keywords();
//
//		AquariumInput aquariumInput = new AquariumInput(metadata);
//		aquariumInput.setUrl("http://www.bing.com");
//
//		instance.fetch(semaphore, aquariumInput);
//		semaphore.acquire(1);
//	}

	@NotNull
	private Metadata getMetadata_broadcrawl(){
		Metadata metadata = new Metadata();
		metadata.setWorkspace("test-workspace");
		metadata.setUow("uow-123435");
		metadata.setCrawlEntityType(Constants.CrawlEntityType.GOOGLE);
		metadata.setJobId("568a8649132ad21be932d3c2");
		metadata.setCrawlType(Constants.CrawlType.BROADCRAWL);
		metadata.setCallbackQueue("callbackqueue");
		metadata.setnResults(10);
		metadata.setTimestamp(System.currentTimeMillis());
		metadata.setStrTimestamp(Long.toString(System.currentTimeMillis()));
		return metadata;
	}

	@NotNull
	private Metadata getMetadata_keywords(){
		Metadata metadata = new Metadata();
		metadata.setWorkspace("test-workspace");
		metadata.setUow("uow-12344321");
		metadata.setCrawlEntityType(Constants.CrawlEntityType.BING);
		metadata.setJobId("568a8649132ad21be932d3c2");
		metadata.setCrawlType(Constants.CrawlType.KEYWORDS);
		metadata.setCallbackQueue("callbackqueue");
		metadata.setnResults(10);
		metadata.setTimestamp(System.currentTimeMillis());
		metadata.setStrTimestamp(Long.toString(System.currentTimeMillis()));
		return metadata;
	}
}