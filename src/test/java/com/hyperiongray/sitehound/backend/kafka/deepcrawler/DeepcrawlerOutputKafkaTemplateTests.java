package com.hyperiongray.sitehound.backend.kafka.deepcrawler;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.Producer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.DdDeepcrawlerOutputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.PageSampleDto;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdDeepcrawlerRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerOutputPagesAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawledIndexService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.deepcrawler.DdDeepcrawlerOutputBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.modeler.input.DdModelerInputService;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import org.assertj.core.util.Lists;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@SpringBootTest
public class DeepcrawlerOutputKafkaTemplateTests {

    private static final String TEMPLATE_TOPIC = "dd-deepcrawler-output";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired private Producer producer;

    @Autowired private DdDeepcrawlerOutputBrokerService ddDeepcrawlerOutputBrokerService;

    @MockBean private AquariumAsyncClient aquariumClient;
    @MockBean private CrawlJobRepository crawlJobRepository;
    @MockBean private CrawledIndexRepository analyzedCrawlResultDtoIndexRepository;
    @MockBean private DdDeepcrawlerRepository ddDeepcrawlerRepository;
    @MockBean private CrawledIndexService crawledIndexService;


    @MockBean
    MongoRepository mongoRepository;
    @MockBean
    GoogleCrawlerBrokerService googleCrawlerBrokerService;
    @MockBean
    BingCrawlerBrokerService bingCrawlerBrokerService;
    @MockBean
    DdModelerInputService ddModelerInputService;
    @MockBean
    HttpProxyClientImpl httpProxyClient;
    @MockBean
    HttpClientConnector httpClientConnector;
    @MockBean
    TikaService tikaService;


    @Test
    public void testTemplate(){

        List<PageSampleDto> pageSamples = new ArrayList<>();

        PageSampleDto pageSampleDto = new PageSampleDto();
        String url = "http://stackexchange.com";
        pageSampleDto.setUrl(url); // has to be valid
        pageSampleDto.setDomain("stackexchange-test.com");
        pageSamples.add(pageSampleDto);

        DdDeepcrawlerOutputDto ddDeepcrawlerOutputDto = new DdDeepcrawlerOutputDto();
        String jobId = "59b0307e166f1c79a63af88a";
        ddDeepcrawlerOutputDto.setId(jobId);
        ddDeepcrawlerOutputDto.setPageSamples(pageSamples);

        JsonMapper<DdDeepcrawlerOutputDto> jsonMapper = new JsonMapper();
        String input = "";
        try {
            input = jsonMapper.toString(ddDeepcrawlerOutputDto);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        CrawlJob crawlJob = new CrawlJob.Builder()
                .withJobId(jobId)
                .withCrawlStatus(Constants.CrawlStatus.STARTED)
                .withWorkspaceId("ws1")
                .withTimestamp(System.currentTimeMillis())
                .withCrawlEntityType(Constants.CrawlEntityType.DD)
                .withCrawlType(Constants.CrawlType.DEEPCRAWL)
                .withNResultsRequested(100)
                .withSources(Lists.newArrayList("DD"))
                .withProgress(false)
                .build();

        when(crawlJobRepository.get(jobId)).thenReturn(crawlJob);

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, ddDeepcrawlerOutputBrokerService, input);

        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
            e.printStackTrace();
        }

        verify(aquariumClient).fetch(eq(url), any());

    }

}