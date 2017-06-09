package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.in;

/**
 * Created by tomas on 11/16/15.
 */
@Repository
public class CrawledTrainingRepository extends GenericCrawlMongoRepository{


	public List<TrainedCrawledUrl> getAllTrainedDocuments(String workspaceId){
		List<TrainedCrawledUrl> trainedCrawledUrls = new LinkedList<>();

		String collectionName = this.getCollectionName(Constants.CrawlType.KEYWORDS);
//		Bson urlFilter=in("url", urls);
		Bson urlFilter=null;

		FindIterable<Document> iterable = mongoRepository.find(collectionName, workspaceId, urlFilter);
		MongoCursor<Document> iterator = iterable.iterator();
		while(iterator.hasNext()){
			trainedCrawledUrls.add(translate(iterator.next()));
		}
		return trainedCrawledUrls;
	}

	public List<String> getRelevantTrainedUrls(String workspaceId){
		List<String> urls = new LinkedList<>();

		String collectionName = this.getCollectionName(Constants.CrawlType.KEYWORDS);
		Bson workspaceFilter = new Document("workspaceId", workspaceId);
		Bson relevantFilter = new Document("relevant", true);
		Bson filter = and(workspaceFilter, relevantFilter);

		FindIterable<Document> iterable = mongoRepository.find(collectionName, workspaceId, filter);
		MongoCursor<Document> iterator = iterable.iterator();
		while(iterator.hasNext()){
			urls.add(iterator.next().getString("url"));
		}
		return urls;
	}

	private TrainedCrawledUrl translate(Document document){
		TrainedCrawledUrl trainedCrawledUrl = new TrainedCrawledUrl();
		trainedCrawledUrl.setUrl(document.getString("url"));
//		trainedCrawledUrl.setContent(document.getString("desc"));
		trainedCrawledUrl.setRelevant(document.getBoolean("relevant"));
		return trainedCrawledUrl;
	}

	public List<String> getPinnedUrls(String workspaceId){
		List<String> urls = new LinkedList<>();
		String collectionName = this.getCollectionName(Constants.CrawlType.BROADCRAWL);
		Bson workspaceFilter = new Document("workspaceId", workspaceId);
		Bson relevantFilter = new Document("pinned", true);
		Bson filter = and(workspaceFilter, relevantFilter);

		FindIterable<Document> iterable = mongoRepository.find(collectionName, workspaceId, filter);
		MongoCursor<Document> iterator = iterable.iterator();
		while(iterator.hasNext()){
			urls.add(iterator.next().getString("url"));
		}
		return urls;
	}

}






/*
	@Autowired
	private MongoRepository mongoRepository;

	private static final String CRAWLED_TRAINING_COLLECTION_NAME_PREFIX = "crawled_training_";
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawledTrainingRepository.class);

	private String getCollectionName(String workspace){
		String collectionSuffix = workspace.replaceAll(" ", "_");
		return CRAWLED_TRAINING_COLLECTION_NAME_PREFIX + collectionSuffix;
	}

	public void save(String workspace, CrawlTraining crawlTraining){
		String collectionName = this.getCollectionName(workspace);
		mongoRepository.insert(collectionName, entityToFieldTranslator(crawlTraining));
	}

	private Map<String, Object> entityToFieldTranslator(CrawlTraining crawlTraining){
		Map<String, Object> document =Maps.newHashMap();
		document.put("url", crawlTraining.getUrl());
		document.put("relevant", crawlTraining.getRelevant());
		document.put("deleted", crawlTraining.getDeleted());
		document.put("crawlEntityType", crawlTraining.getCrawlEntityType().toString());
		return document;
	}

	public List<String> filterByUrl(String workspace, List<String> urls){
		String collectionName = this.getCollectionName(workspace);
		return  mongoRepository.filterByUrl(collectionName, urls);
	}

*/
