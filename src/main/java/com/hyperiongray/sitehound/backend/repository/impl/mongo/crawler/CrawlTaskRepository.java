package com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler;

import com.google.common.collect.Maps;
import com.hyperiongray.sitehound.backend.model.CrawlTask;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by tomas on 11/16/15.
 */
@Repository
public class CrawlTaskRepository{

	@Autowired
	private MongoRepository mongoRepository;

	private static final String CRAWL_TASK_COLLECTION_NAME = "crawl_task";
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlTaskRepository.class);

	public void save(String workspaceId, CrawlTask crawlTask){
		LOGGER.info("About to save crawlTask:" + crawlTask);
		Map<String, Object> fields = entityToFields(crawlTask);
		mongoRepository.insert(CRAWL_TASK_COLLECTION_NAME, workspaceId, fields);
	}

	public Map<String, Object> entityToFields(CrawlTask crawlTask){
		Map<String, Object> document = Maps.newHashMap();
		document.put("jobId", crawlTask.getJobId());
		document.put("taskId", crawlTask.getTaskId());
		document.put("source", crawlTask.getCrawlEntityType().toString());
		document.put("nResultsFound", crawlTask.getnResultsFound());
		return document;
	}
}