package com.hyperiongray.sitehound.backend.kafka.keywords;

import com.google.common.collect.Lists;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.crawler.dispatcher.KeywordsCrawlerMessageDispatcher;
import com.hyperiongray.sitehound.backend.service.crawler.excavator.ExcavatorSearchService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.modeler.input.DdModelerInputService;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import com.hyperiongray.sitehound.backend.test.kafka.producer.Producer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@SpringBootTest
public class KeywordsMessageDispatcherTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsMessageDispatcherTest.class);

    private static final String TEMPLATE_TOPIC = "google-keywords-input";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private Producer producer;

    @Autowired private KeywordsCrawlerMessageDispatcher instance;

    private JsonMapper<SubscriberInput> jsonMapper = new JsonMapper();

    @MockBean MongoRepository mongoRepository;
    @MockBean GoogleCrawlerBrokerService googleCrawlerBrokerService;
    @MockBean BingCrawlerBrokerService bingCrawlerBrokerService;
    @MockBean DdModelerInputService ddModelerInputService;
    @MockBean HttpProxyClientImpl httpProxyClient;
    @MockBean HttpClientConnector httpClientConnector;
    @MockBean TikaService tikaService;
    @MockBean ElasticsearchDatabaseClient elasticsearchDatabaseClient;
    @MockBean ExcavatorSearchService excavatorSearchService;
    @MockBean AquariumAsyncClient aquariumClient;

    @MockBean private CrawlJobRepository crawlJobRepository;


    @Captor
    private ArgumentCaptor<Set<String>> inputCaptor;

    @Test
    public void templateTest(){
//        SubscriberInput subscriberInput = jsonMapper.toObject(data, SubscriberInput.class);
        int nResults = 100;
        ArrayList<String> included = Lists.newArrayList("bitcoin");

        SubscriberInput subscriberInput = new SubscriberInput();
        subscriberInput.setCrawlProvider("HH_JOOGLE");
        subscriberInput.setCrawlSources(Lists.newArrayList("TOR"));
        subscriberInput.setIncluded(Lists.newArrayList(included));
        subscriberInput.setExistentUrl(Lists.newArrayList());
        subscriberInput.setExcluded(Lists.newArrayList());
        subscriberInput.setJobId("12345678");
        subscriberInput.setWorkspace("11111111");
        subscriberInput.setnResults(nResults);
        subscriberInput.setStrTimestamp("la hora");
        subscriberInput.setTimestamp(System.currentTimeMillis());
        subscriberInput.setRelevantUrl(Lists.newArrayList());


        when(crawlJobRepository.updateJobStatus(any(), any())).thenReturn(true);
        try {
            String input = jsonMapper.toString(subscriberInput);
            producer.produce(TEMPLATE_TOPIC, embeddedKafka, instance, input);


        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }


        try {
            verify(excavatorSearchService).search(inputCaptor.capture(), eq(0), eq(nResults));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(included.size(), inputCaptor.getValue().size());
        assertEquals(included.contains("bitcoin"), inputCaptor.getValue().contains("bitcoin"));

    }


}
