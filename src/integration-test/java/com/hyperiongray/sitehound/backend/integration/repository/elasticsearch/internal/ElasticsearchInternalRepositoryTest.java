package com.hyperiongray.sitehound.backend.integration.repository.elasticsearch.internal;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.integration.repository.elasticsearch.excavator.ExcavatorSearchTest;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.*;
import com.hyperiongray.sitehound.backend.service.utils.Encoders;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository.CRAWLED_INDEX_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository.CRAWLED_TYPE_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("integration-test")
@TestPropertySource(locations = {"file:config/properties/elasticsearch.properties"})
public class ElasticsearchInternalRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchInternalRepositoryTest.class);

    @Autowired private ElasticsearchDatabaseClient elasticsearchDatabaseClient;


    @Test
    public void get() throws Exception {

        //saving: http://judfqk3c4jittudj.onion/international/l10n/po/ca->http%3A%2F%2Fjudfqk3c4jittudj.onion%2Finternational%2Fl10n%2Fpo%2Fca
//        String url = "http://www.example.com";
        String url = "http://judfqk3c4jittudj.onion/international/l10n/po/ca";
        String id = Encoders.encodeUrl(url);
        LOGGER.info("getting: " + url + "->" + id);

        Optional<String> stringOptional = elasticsearchDatabaseClient.get(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, id);


        System.out.println(stringOptional);
    }

}