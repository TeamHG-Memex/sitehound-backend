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
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Assert;
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


	public void save(RestHighLevelClient client, String index, String type, String id, String source) throws IOException{
	    LOGGER.info("saving: (index:" + index + "), (type:" + type + "), (id:" + id + ")");
        IndexRequest indexRequest = new IndexRequest(index, type, id.toLowerCase());
        indexRequest.source(source, XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest);
    }


	public Optional<String> get(RestHighLevelClient client, String index, String type, String id) throws IOException{

        Optional<String> result;
        GetRequest request = new GetRequest(index, type, id.toLowerCase());
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

}
