package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ElasticsearchRepositoryTemplate{

	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchRepositoryTemplate.class);

	public void createIndex(JestClient client, String name) throws IOException{
		client.execute(new CreateIndex.Builder(name).build());
	}

	public <T> void save(JestClient client, String indexName, String type, String id, T source) throws IOException{
		id = id.toLowerCase();
		Index index = new Index.Builder(source).index(indexName).type(type).id(id).build();
		DocumentResult documentResult = client.execute(index);
		if(!documentResult.isSucceeded()){
			throw new RuntimeException(documentResult.getErrorMessage());
		}
	}

	public void upsert(JestClient client, String indexName, String type, String id, String script) throws IOException{
		id = id.toLowerCase();
		Update update = new Update.Builder(script).index(indexName).type(type).id(id).build();
		DocumentResult documentResult = client.execute(update);
//		System.out.println(documentResult.getErrorMessage());
		if(!documentResult.isSucceeded()){
			throw new RuntimeException(documentResult.getErrorMessage());
		}
	}

	public<T> T get(JestClient client, String indexName, String typeName, String id, Class<T> typeParameterClass) throws IOException{
		id = id.toLowerCase();
		Get get = new Get.Builder(indexName, id).type(typeName).build();
		JestResult result = client.execute(get);
		T t  = result.getSourceAsObject(typeParameterClass);
		return t;
	}

	public int delete(JestClient client, String indexName, String typeName, String id) throws IOException{
		id = id.toLowerCase();
		DocumentResult documentResult = client.execute(new Delete.Builder(id)
															.index(indexName)
															.type(typeName)
															.build());
		return documentResult.isSucceeded() ? 1 : 0;
	}


/*
	//TODO
	public Object search(List<String> included, int startingFrom, int pageSize) throws IOException{

		StringBuilder sb = new StringBuilder();
		sb.append(CrawlerUtils.groupCreator(included));

		String query = "{"
//               + "    \"from\":0, \"size\":100,"
//               + "    \"fields\" : [\"url\", \"domain\"],"
				               + "    \"query\": {"
//               + "        \"from\" : 0, \"size\" : 10,"
				               + "        \"filtered\" : {"
				               + "            \"query\" : {"
				               + "                \"query_string\" : {"
				               + "					\"fields\" : [\"title^6\", \"links^2\", \"h1^3\", \"h2^3\", \"text^1\"],"
				               + "                  \"query\" : \""+ sb.toString() + "\","
				               + "		            \"use_dis_max\" : true"
				               + "                }"
				               + "            },"
				               + "            \"filter\" : {\"exists\" : { \"field\" : \"url\" }"
				               + "            }"
				               + "        }"
				               + "    }"
				               + "}";


		Search search = new Search.Builder(query).addIndex("elasticsearch").addType("onions")
				                .setSearchType(SearchType.QUERY_THEN_FETCH)
				                .setParameter("from", startingFrom)
				                .setParameter("size", pageSize)
				                .build();

		SearchResult result = client.execute(search);
		LOGGER.debug(result.getJsonString());
		List<TorCrawlerResult> torCrawlerResults = Lists.newArrayList();
		Integer resultTotal=result.getTotal();
		LOGGER.info("total results from tor: " + resultTotal);

		return null;
	}
	*/
}
