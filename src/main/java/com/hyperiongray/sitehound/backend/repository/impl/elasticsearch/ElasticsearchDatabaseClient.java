package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultWrapperDto;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Repository
public class ElasticsearchDatabaseClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchDatabaseClient.class);

    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private String port;

    @Autowired
    private ElasticsearchRepositoryTemplate elasticsearchRepositoryTemplate;

    private JestClient client;

    @PostConstruct
    public void postConstruct() throws KeyManagementException, NoSuchAlgorithmException {
        String elasticSearchUri = "http://" + host + ":" + port;
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(elasticSearchUri)
                .connTimeout(120 * 1000)
                .readTimeout(120 * 1000)
                .multiThreaded(true)
                .build());
        client = factory.getObject();
    }


    public <T> void save(String indexName, String type, String id, T source) throws IOException {
        elasticsearchRepositoryTemplate.save(client, indexName, type, id, source);
    }

    public void upsert(String indexName, String typeName, String id, String script) throws IOException {
        elasticsearchRepositoryTemplate.upsert(client, indexName, typeName, id, script);
    }

    public <T> T get(String indexName, String typeName, String id, Class<T> typeParameterClass) throws IOException {
        return elasticsearchRepositoryTemplate.get(client, indexName, typeName, id, typeParameterClass);
    }

    public void delete(String indexName, String typeName, String id) throws IOException {
        elasticsearchRepositoryTemplate.delete(client, indexName, typeName, id);
    }
}