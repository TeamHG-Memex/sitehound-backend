package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;


import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultWrapperDto;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class CrawledIndexHttpRepository implements CrawledIndexRepository{
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawledIndexHttpRepository.class);

	public final static String CRAWLED_INDEX_NAME ="crawled-open";
	public final static String CRAWLED_TYPE_NAME ="analyzed";

	@Autowired private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

	private final JsonMapper<AnalyzedCrawlResultDto> jsonMapper = new JsonMapper();

//	@Override
	public String save(String url, String workspace, Constants.CrawlEntityType crawlEntityType, AnalyzedCrawlResultDto analyzedCrawlResultDto) throws IOException{
		AnalyzedCrawlResultWrapperDto analyzedCrawlResultWrapperDto = new AnalyzedCrawlResultWrapperDto();
		analyzedCrawlResultWrapperDto.setResult(analyzedCrawlResultDto);
		analyzedCrawlResultWrapperDto.setWorkspaces(Sets.<String>newHashSet(workspace));
		elasticsearchDatabaseClient.save(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, url, analyzedCrawlResultWrapperDto);
		return url;
	}
		/**
		 * This method does an upsert, adding the new documenent if not already exists.
		 * Otherwise the current workspace will be added to the list of workspaces in the indexed document
		 */
//	@Override
	public String upsert(String url, String workspace, Constants.CrawlEntityType crawlEntityType, AnalyzedCrawlResultDto analyzedCrawlResultDto) throws IOException{
		LOGGER.info("saving: " + url);
		//save(indexName, typeName, id, analyzedCrawlResultDto);

		String webType = (crawlEntityType == Constants.CrawlEntityType.TOR ? "deep" : "open");
		String json = jsonMapper.toString(analyzedCrawlResultDto);
		String script = "{" +
							" \"script\" : \"ctx._source.workspaces.contains(workspace) ? ctx._source.workspaces = ctx._source.workspaces : (ctx._source.workspaces += workspace) \"," +
							" \"params\" : {" +
								" \"workspace\" : \"" + workspace + "\""+
							" }," +
							" \"upsert\" : { "+
								" \"workspaces\" : [\"" + workspace + "\"]," +
								" \"webType\" : \"" + webType + "\"," +
								" \"result\" :" + json +
							" }" +
							"}" +
						"}";
//		String id = UuidGenerator.hash(url);
		String id = url;
		elasticsearchDatabaseClient.upsert(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, id, script);
		return id;
	}

//	@Override
	public AnalyzedCrawlResultDto get(String url) throws IOException{
		LOGGER.info("getting: " + url);
//		String id = UuidGenerator.hash(url);
		String id = url;
		AnalyzedCrawlResultWrapperDto analyzedCrawlResultWrapperDto = elasticsearchDatabaseClient.get(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, id, AnalyzedCrawlResultWrapperDto.class);
		return analyzedCrawlResultWrapperDto == null ? null : analyzedCrawlResultWrapperDto.getResult();

	}

//	@Override
	public void delete(String url) throws IOException{
//		String id = UuidGenerator.hash(url);
		String id = url;
		elasticsearchDatabaseClient.delete(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, id);
	}

}
