package com.hyperiongray.sitehound.backend.integration.excavator;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.AbstractElasticsearchRepository;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Service
public class ExcavatorClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcavatorClient.class);

    @Value( "${excavator.host}" ) private String host;
    @Value( "${excavator.port}" ) private String port;
    @Value( "${excavator.user}" ) private String user;
    @Value( "${excavator.password}" ) private String password;

    private JestClient client;

    @PostConstruct
    public void postConstruct() throws KeyManagementException, NoSuchAlgorithmException {
//		String elasticSearchUri = "http://localhost:9200";
        String elasticSearchUri = "http://" + host + ":" + port;
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(elasticSearchUri)
                .connTimeout(120 * 1000)
                .readTimeout(120 * 1000)
                .multiThreaded(true)
                .build());
        client = factory.getObject();
    }

}
