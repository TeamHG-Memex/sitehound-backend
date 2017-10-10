package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestResult;
//import io.searchbox.core.*;
//import io.searchbox.indices.CreateIndex;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


@Component
public class ElasticsearchRepositoryTemplate{

	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchRepositoryTemplate.class);

//	public void createIndex(RestHighLevelClient client, String name) throws IOException{
//		client.execute(new CreateIndex.Builder(name).build());
//	}

	public void save(RestHighLevelClient client, String index, String type, String id, String source) throws IOException{
//		id = id.toLowerCase();
//		Index index = new Index.Builder(source).index(indexName).type(type).id(id).build();
//		DocumentResult documentResult = client.execute(index);
//		if(!documentResult.isSucceeded()){
//			throw new RuntimeException(documentResult.getErrorMessage());
//		}

        IndexRequest indexRequest = new IndexRequest(index, type, id.toLowerCase());
        indexRequest.source(source, XContentType.JSON);
        client.index(indexRequest);
	}

	/*
	public void upsert(RestHighLevelClient client, String index, String type, String id, String script) throws IOException{
//		id = id.toLowerCase();
//		Update update = new Update.Builder(script).index(indexName).type(type).id(id).build();
//		DocumentResult documentResult = client.execute(update);
////		System.out.println(documentResult.getErrorMessage());
//		if(!documentResult.isSucceeded()){
//			throw new RuntimeException(documentResult.getErrorMessage());
//		}
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index);
        updateRequest.type(type);
        updateRequest.id(id);
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("gender", "male")
                .endObject());
        client.update(updateRequest).get();
	}
*/
	public Optional<String> get(RestHighLevelClient client, String index, String type, String id) throws IOException{

        Optional<String> result;
        GetRequest request = new GetRequest(index, type, id);
        try {

            GetResponse response = client.get(request);
            if (response.isExists()) {
                String sourceAsString = response.getSourceAsString();
                result = Optional.of(sourceAsString);
            } else{
                result = Optional.empty();
            }

        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
               result = Optional.empty();
            }
            else{
                LOGGER.warn("Elasticsearch Error", e);
               result = Optional.empty();
            }
        }
        return result;
    }

	public void delete(RestHighLevelClient client, String indexName, String typeName, String id) throws IOException{
        DeleteRequest deleteRequest = new DeleteRequest(indexName, typeName, id.toLowerCase());
        DeleteResponse response = client.delete(deleteRequest);
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
