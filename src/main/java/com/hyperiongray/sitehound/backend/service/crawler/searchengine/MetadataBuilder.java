package com.hyperiongray.sitehound.backend.service.crawler.searchengine;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tomas on 2/4/16.
 */
@Component
public class MetadataBuilder{

	@Autowired private CrawlJobRepository crawlJobRepository;

	public static Metadata build(SubscriberInput subscriberInput, Constants.CrawlType crawlType, Constants.CrawlEntityType crawlEntityType){
		Metadata metadata = new Metadata();
		metadata.setCrawlType(crawlType);
		metadata.setSource(subscriberInput.getSource());
		metadata.setStrTimestamp(subscriberInput.getStrTimestamp());
		metadata.setWorkspace(subscriberInput.getWorkspace());
		metadata.setTimestamp(subscriberInput.getTimestamp());
		metadata.setCallbackQueue(subscriberInput.getCallbackQueue());
		metadata.setJobId(subscriberInput.getJobId());
		metadata.setCrawlEntityType(crawlEntityType);
		metadata.setnResults(subscriberInput.getnResults());
		return metadata;
	}

	public Metadata buildFromTrainerOutputPages(String jobId){
		Metadata metadata = new Metadata();
		metadata.setCrawlType(Constants.CrawlType.KEYWORDS);
		metadata.setSource("DD");
		metadata.setStrTimestamp(String.valueOf(System.currentTimeMillis()));
		metadata.setWorkspace(crawlJobRepository.getWorkspaceId(jobId));
		metadata.setTimestamp(System.currentTimeMillis());
		metadata.setCallbackQueue("");
		metadata.setJobId("generic-job-id");
		metadata.setCrawlEntityType(Constants.CrawlEntityType.DD);
		metadata.setnResults(30);
		return metadata;
	}

	public Metadata buildFromCrawlerOutput(String jobId){
		Metadata metadata = new Metadata();
		metadata.setCrawlType(Constants.CrawlType.BROADCRAWL);
		metadata.setSource("DD");
		metadata.setStrTimestamp(String.valueOf(System.currentTimeMillis()));
		metadata.setWorkspace(crawlJobRepository.getWorkspaceId(jobId));
		metadata.setTimestamp(System.currentTimeMillis());
		metadata.setCallbackQueue("");
		metadata.setJobId("generic-broadcrawl-job-id");
		metadata.setCrawlEntityType(Constants.CrawlEntityType.DD);
		metadata.setnResults(1000);
		return metadata;
	}

	public Metadata buildFromDeepcrawlerOutput(String jobId){
		Metadata metadata = new Metadata();
		metadata.setCrawlType(Constants.CrawlType.DEEPCRAWL);
		metadata.setSource("DD");
		metadata.setStrTimestamp(String.valueOf(System.currentTimeMillis()));
		metadata.setWorkspace(crawlJobRepository.getWorkspaceId(jobId));
		metadata.setTimestamp(System.currentTimeMillis());
		metadata.setCallbackQueue("");
		metadata.setJobId(jobId);
		metadata.setCrawlEntityType(Constants.CrawlEntityType.DD);
		metadata.setnResults(1000);
		return metadata;
	}


}
