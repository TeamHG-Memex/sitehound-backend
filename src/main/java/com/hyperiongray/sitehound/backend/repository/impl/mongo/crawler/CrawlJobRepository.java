package com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputProgress;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository.JOB_COLLECTION_NAME;
import static com.mongodb.client.model.Updates.combine;

/**
 * Created by tomas on 11/16/15.
 */
@Repository
public class CrawlJobRepository{
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlJobRepository.class);

	@Autowired
	private MongoRepository mongoRepository;

	public static final String CRAWL_JOB_COLLECTION_NAME = "crawl_job";
	private ConcurrentHashMap<String, String> cacheMap = new ConcurrentHashMap<>(50);


	public Optional<CrawlJob> get(String id){
		LOGGER.info("About to get crawlJob:" + id);
		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(CRAWL_JOB_COLLECTION_NAME);
		Bson filter = new BasicDBObject("_id", new ObjectId(id));
		FindIterable<Document> iterable = collection.find(filter);
		Document document = iterable.first();

		Optional<CrawlJob> result;
		if(document==null){
			result = Optional.empty();
			LOGGER.warn("Crawl Job doesn't exists!!!!!!!!!!!:" + id);

		}
		else{
			CrawlJob crawlJob = new CrawlJob.Builder()
					.withWorkspaceId(document.getString("workspaceId"))
					.withJobId(id)
					.withSources((List<String>) document.get("sources"))
					.withCrawlType(Constants.CrawlType.valueOf(document.getString("crawlType")))
					.withCrawlEntityType(Constants.CrawlEntityType.DD)
					.withCrawlStatus(Constants.CrawlStatus.valueOf(document.getString("status")))
					.withTimestamp(document.getDouble("timestamp").longValue())
					.withNResultsRequested(document.getInteger("nResultsRequested"))
					.withProgress(document.containsKey("progress"))
					.build();
			result = Optional.of(crawlJob);
		}
		return result;
	}


	public boolean updateJobStatus(String jobId, Constants.JobStatus status){
		LOGGER.info("About to update crawlJob:" + jobId);
		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(CRAWL_JOB_COLLECTION_NAME);
//		Bson filter = new BasicDBObject("_id", new ObjectId(jobId)).append("status", "QUEUED");
		Bson filter = new BasicDBObject("_id", new ObjectId(jobId));
		UpdateResult updateResult=collection.updateOne(filter,
				new BasicDBObject("$set", new BasicDBObject("status", status.toString())));
		LOGGER.info("Finished to update crawlJob:" + jobId);
		return updateResult.getModifiedCount() == 1L;
	}

	public boolean isJobActive(String jobId){
		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(CRAWL_JOB_COLLECTION_NAME);
		Bson filter = new BasicDBObject("_id", new ObjectId(jobId));
		FindIterable<Document> iterable = collection.find(filter);
		MongoCursor<Document> iterator = iterable.iterator();
		if(iterator.hasNext()){
			String status= iterator.next().getString("status");
			if(Constants.JobStatus.STOPPED.toString().equalsIgnoreCase(status)){
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	public String getWorkspaceId(String jobId){
		return cacheMap.computeIfAbsent(jobId, k ->getWorkspaceIdNoCache(k));
	}

	private String getWorkspaceIdNoCache(String jobId){
		LOGGER.info("About to update crawlJob:" + jobId);
		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(CRAWL_JOB_COLLECTION_NAME);
		Bson filter = new BasicDBObject("_id", new ObjectId(jobId));
		FindIterable<Document> iterable = collection.find(filter);
		return iterable.iterator().next().getString("workspaceId");
	}

//	public void saveProgress(String jobId, Double percentageDone) {
////		String jobId = ddTrainerOutputProgress.getWorkspaceId();
//		LOGGER.info("About to update crawlJob:" + jobId);
//		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(CRAWL_JOB_COLLECTION_NAME);
//		Bson filter = new BasicDBObject("_id", new ObjectId(jobId));
//		Bson updatesModel = Updates.set("percentage_done",percentageDone);
//		collection.findOneAndUpdate(filter, updatesModel);
//	}


	public void saveProgress(DdCrawlerOutputProgress ddCrawlerOutputProgress) {
		LOGGER.info("About to update crawler progress:" + ddCrawlerOutputProgress.getId());

		Bson filter = Filters.eq("_id", new ObjectId(ddCrawlerOutputProgress.getId()));
		Bson updateProgress = Updates.set("progress", ddCrawlerOutputProgress.getProgress());
		Bson updatePercentageDone = Updates.set("percentage_done", ddCrawlerOutputProgress.getPercentageDone());
		Bson combinedUpdate = combine(updateProgress, updatePercentageDone);

		MongoCollection<Document> collection = mongoRepository.getCollection(JOB_COLLECTION_NAME);
		collection.findOneAndUpdate(filter, combinedUpdate);

		LOGGER.info("updating field : " + ddCrawlerOutputProgress.getId() + " in: "+ JOB_COLLECTION_NAME);
	}

}
