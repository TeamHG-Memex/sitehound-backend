package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestClientFactory;
//import io.searchbox.client.config.HttpClientConfig;


@Repository
public class ElasticsearchDatabaseClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchDatabaseClient.class);


    @Value( "${elasticsearch.scheme}" ) private String scheme;
    @Value( "${elasticsearch.host}" ) private String host;
    @Value( "${elasticsearch.port}" ) private int port;
    @Value( "${elasticsearch.index}" ) private String index;

    @Autowired private ElasticsearchRepositoryTemplate elasticsearchRepositoryTemplate;

    private RestHighLevelClient client;

    @PostConstruct
    public void postConstruct(){
        RestClient lowLevelRestClient = RestClient.builder(new HttpHost(host, port, scheme)).build();
        client = new RestHighLevelClient(lowLevelRestClient);
        //TODO   restClient.close(); when the app closes!
    }

    public void save(String indexName, String type, String id, String source) throws IOException {
        elasticsearchRepositoryTemplate.save(client, indexName, type, id, source);
    }

//    public void upsert(String indexName, String typeName, String id, String script) throws IOException {
//        elasticsearchRepositoryTemplate.upsert(client, indexName, typeName, id, script);
//    }

    public Optional<String> get(String indexName, String typeName, String id) throws IOException {
        return elasticsearchRepositoryTemplate.get(client, indexName, typeName, id);
    }

    public void delete(String indexName, String typeName, String id) throws IOException {
        elasticsearchRepositoryTemplate.delete(client, indexName, typeName, id);
    }

}
