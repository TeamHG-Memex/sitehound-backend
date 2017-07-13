package com.hyperiongray.sitehound.backend.repository.impl.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.in;

/**
 * Created by tomas on 9/20/15.
 */
@Repository
public class MongoRepository{
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoRepository.class);

	protected static final String SEED_URLS_COLLECTION_NAME ="seed_urls";
	protected static final String BROAD_CRAWLER_COLLECTION_NAME ="broad_crawler";

	@Value( "${mongo.host}" ) private String host;
	@Value( "${mongo.port}" ) private Integer port;
	@Value( "${mongo.db}" ) private String db;

	@Autowired
	private MongoClient mongoClient;

	@Bean
	public MongoClient mongo() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(host, port);
		return mongoClient;
	}

	public MongoDatabase getDatabase(){
		return  mongoClient.getDatabase(db);
	}

	public FindIterable<Document> find(String collectionName, String workspaceId, Bson filter){
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		FindIterable<Document> iterable;
		if(filter==null){
			iterable=collection.find(new Document("workspaceId", workspaceId));
		}
		else{
			iterable=collection.find(filter);
		}
		return iterable;
	}


	public boolean exists(String collectionName, String workspaceId, String url){
		LOGGER.info("exist? collectionName:" + collectionName + ", url: " +url);
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		FindIterable<Document> iterable = collection.find(new Document("url", url).append("workspaceId", workspaceId));
		return iterable.iterator().hasNext();
	}

	/**
	 * Creates a filter that matches all documents where the value of a field equals any value in the list of specified values.
	 * @param collectionName
	 * @param urls
	 * @return the urls not yet downloaded
	 */
	public List<String> filterByUrl(String collectionName, String workspaceId, List<String> urls){
		LOGGER.info("filterByUrl collectionName:" + collectionName + ", url: " +urls.size());
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		Bson urlFilter1=in("url", urls);
		Bson urlFilter2=new Document("workspaceId", workspaceId);
		Bson urlFilter = and(urlFilter1, urlFilter2);
		FindIterable<Document> iterable = collection.find(urlFilter);
		MongoCursor<Document> iterator = iterable.iterator();
		List<String> filterResults = new LinkedList<>();
		while(iterator.hasNext()){
			Document next=iterator.next();
			filterResults.add(next.get("url").toString());
		}
		return filterResults;
	}

	public void insert(String collectionName, String workspaceId, Map<String, Object> fields){
		LOGGER.info("inserting collectionName:" + collectionName);

		try{
			MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
			Document document = new Document();
			document.putAll(fields);
			document.put("workspaceId", workspaceId);
			collection.insertOne(document);
			LOGGER.info("inserted collectionName:" + collectionName );
		}
		catch(MongoWriteException mwe){
			LOGGER.warn("URL already existed" + fields);
//			throw (mwe);
		}
		catch(Exception e){
			LOGGER.error("Failed to save: collectionName: " + collectionName+ " fields: " +  fields, e);
			throw (new RuntimeException("FAILED TO SAVE", e));
		}
	}


	public void updateFieldsInDocument(String collectionName, String objectId, String key, Document document){
		LOGGER.info("updating field : " + key + " in: "+ collectionName);
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		Bson filter = Filters.eq("_id", new ObjectId(objectId));
		Bson updates = Updates.set(key, document);
		collection.findOneAndUpdate(filter, updates);
	}

	public Document getById(String collectionName, String objectId){
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		Bson filter = Filters.eq("_id", new ObjectId(objectId));
		FindIterable<Document> iterables = collection.find(filter);
		if(iterables.iterator().hasNext()){
			return iterables.iterator().next();
		}
		else{
			return new Document();
		}
	}


}