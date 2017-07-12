package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.or;

/**
 * Created by tomas on 11/16/15.
 */
@Repository
public class KeywordsRepository extends GenericCrawlMongoRepository{
	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsRepository.class);

	private static final String KEYWORDS_COLLECTION_NAME = "seed_urls";

	@Autowired private CrawlJobRepository crawlJobRepository;

	public List<TrainedCrawledUrl> getTrainedDocuments(String workspaceId){
		List<TrainedCrawledUrl> trainedCrawledUrls = new LinkedList<>();
		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(KEYWORDS_COLLECTION_NAME);
		FindIterable<Document> documents = collection.find(
				Filters.and(
						Filters.eq("workspaceId", workspaceId),
						Filters.nin("deleted", true, false),
						Filters.in("relevant", true, false)
		));
		MongoCursor<Document> iterator = documents.iterator();
		while(iterator.hasNext()){
			trainedCrawledUrls.add(translate(iterator.next()));
		}
		LOGGER.info("returning trainedCrawledUrls: " + trainedCrawledUrls.size());
		return trainedCrawledUrls;
	}

	public List<String> getAllUrls(String workspaceId){
		List<String> urls = new LinkedList<>();
		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(KEYWORDS_COLLECTION_NAME);
		FindIterable<Document> documents = collection.find(
				Filters.and(
						Filters.eq("workspaceId", workspaceId),
						Filters.nin("deleted", true, false)
				));
		MongoCursor<Document> iterator = documents.iterator();
		while(iterator.hasNext()){
			urls.add(iterator.next().getString("url"));
		}
		LOGGER.info("returning allUrls: " + urls.size());
		return urls;
	}

	public List<String> getRelevantTrainedUrls(String workspaceId){
		List<String> urls = new LinkedList<>();

		MongoCollection<Document> collection = mongoRepository.getDatabase().getCollection(KEYWORDS_COLLECTION_NAME);
		FindIterable<Document> documents = collection.find(
				Filters.and(
						Filters.eq("workspaceId", workspaceId),
						Filters.nin("deleted", true, false),
						Filters.in("relevant", true)
				));
		MongoCursor<Document> iterator = documents.iterator();
		while(iterator.hasNext()){
			urls.add(iterator.next().getString("url"));
		}
		LOGGER.info("returning relevantTrainedUrls: " + urls.size());
		return urls;
	}

	private TrainedCrawledUrl translate(Document document){
		TrainedCrawledUrl trainedCrawledUrl = new TrainedCrawledUrl(document.getString("url"), document.getBoolean("relevant"));
		return trainedCrawledUrl;
	}





}
