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

	@Autowired
    AquariumBrokerService instance;

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