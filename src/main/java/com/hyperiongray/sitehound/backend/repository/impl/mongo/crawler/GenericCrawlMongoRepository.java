package com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler;

import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.mongodb.MongoWriteException;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 11/16/15.
 */
@Repository
public class GenericCrawlMongoRepository{

	@Autowired
	protected MongoRepository mongoRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericCrawlMongoRepository.class);

	public void save(Constants.CrawlType crawlType, String workspaceId, Map<String, Object> fields){

		try{
			String collectionName = getCollectionName(crawlType);
			mongoRepository.insert(collectionName, workspaceId, fields);
		}
		catch(MongoWriteException mwe){
			LOGGER.warn("URL already existed" + fields);
		}
		catch(Exception e){
			LOGGER.error("Failed to save: crawltype: " + crawlType + " workspace: " +  workspaceId, e);
		}
	}


	public boolean exists(String workspaceId, Constants.CrawlType crawlType, String url){
		String collectionName = this.getCollectionName(crawlType);
		return  mongoRepository.exists(collectionName, workspaceId, url);
	}

	public List<String> filterByUrl(String workspaceId, Constants.CrawlType crawlType, List<String> urls){
		String collectionName = this.getCollectionName(crawlType);
		return  mongoRepository.filterByUrl(collectionName, workspaceId, urls);
	}

	@NotNull
	public String getCollectionName(Constants.CrawlType crawlType){
		String collection;
		if(crawlType == Constants.CrawlType.KEYWORDS){
			collection = mongoRepository.SEED_URLS_COLLECTION_NAME;
		}
		else if(crawlType == Constants.CrawlType.BROADCRAWL){
			collection = mongoRepository.BROAD_CRAWLER_COLLECTION_NAME;
		}
		else if(crawlType == Constants.CrawlType.DEEPCRAWL){
			collection = mongoRepository.DEEP_CRAWLER_COLLECTION_NAME;
		}
		else{
			throw new RuntimeException("Unsupported CrawlType!");
		}
		return collection;
	}





/**
 * { "_id" : ObjectId("55b91e315d2e8451d3a38840"),
 * "urlDesc" : "UK Guns and Ammo Store - Buy guns and ammo in the UK for Bitcoin.",
 * "url" : "http://mxbdg4xeduactb66.onion/",
 * "host" : "mxbdg4xeduactb66.onion",
 * "score" : 100,
 * "words" : [ "guns", "ammo", "gbp", "9mm", "318", "store", "buy", "walther", "glock", "quantity", "unused", "bitcoin", "100", "bullets", "p99", "product", "new", "price", "132", "179" ],
 * "desc" : "UK Guns and Ammo Store - Buy guns and ammo in the UK for Bitcoin. ![UK Guns and Ammo Store - Buy gun",
 * "relevant" : null }


 { "_id" : ObjectId("55a670965d2e8420cb54fd1d"),
 "url" : "http://www.chicagobusiness.com/article/20150606/ISSUE01/306069991/why-this-lawyer-is-rethinking-patent-lawsuits",
 "urlDesc" : "Why this <b>lawyer</b> is rethinking <b>patent</b> lawsuits - Crain's Chicago <b>...</b>",
 "host" : "chicagobusiness.com",
 "score" : 5,
 "words" : [ "patent", "says", "litigation", "chicago", "law", "budget", "new", "niro", "business", "proceedings", "firms", "court", "cuts", "office", "tech", "year", "news", "percent", "million", "cases" ],
 "categories" : [ "news" ],
 "desc" : "Jun 6, 2015 <b>...</b> The A-<b>List</b> ... A Chicago <b>lawyer</b> who made his name representing “<b>patent</b> trolls” is <br> pulling ... clients whose <b>patents</b> were being <b>infringed</b>, says Raymond Niro, <br> founder of Niro ... Last year, the number of new <b>patent cases filed</b> in U.S. District <br> ... The 2011 <b>law</b> introduced new <b>review</b> proceedings before the <b>Patent</b>&nbsp;...",
 "relevant" : false }

 */

}
