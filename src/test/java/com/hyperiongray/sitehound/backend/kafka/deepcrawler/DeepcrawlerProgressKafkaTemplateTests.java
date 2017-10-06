package com.hyperiongray.sitehound.backend.kafka.deepcrawler;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.Producer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DdDeepcrawlerProgressDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DomainDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.ProgressDto;
import com.hyperiongray.sitehound.backend.model.CrawlJob;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdDeepcrawlerRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdDeepcrawlerOutputPagesAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.deepcrawler.DdDeepcrawlerProgressBrokerService;
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
public class DeepcrawlerProgressKafkaTemplateTests {

    private static final String TEMPLATE_TOPIC = "dd-deepcrawler-progress";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private Producer producer;

    @Autowired private DdDeepcrawlerProgressBrokerService instance;

    @MockBean private CrawlJobRepository crawlJobRepository;
    @MockBean private DdDeepcrawlerRepository ddDeepcrawlerRepository;
    @MockBean private DdDeepcrawlerOutputPagesAquariumCallbackService ddDeepcrawlerOutputPagesAquariumCallbackService;
    @MockBean private AquariumAsyncClient aquariumClient;

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
    public void processTest() throws Exception {

        DdDeepcrawlerProgressDto ddDeepcrawlerProgressDto = new DdDeepcrawlerProgressDto();
        String id = "59a18279166f1c48c18760c5";
        ddDeepcrawlerProgressDto.setId(id);

        ProgressDto progressDto = new ProgressDto();
        progressDto.setPagesFetched(1000);
        progressDto.setRpm(1000);
        progressDto.setPagesFetched(155565);
        progressDto.setStatus("running");
        List<DomainDto> domains = Lists.newArrayList();

        DomainDto domainDto = new DomainDto();
        domainDto.setDomain("domain1");
        domainDto.setPagesFetched(1999);
        domainDto.setRpm(100);
        domainDto.setStatus("started");
        domainDto.setUrl("http://stackexchange.com");
        domains.add(domainDto);
        progressDto.setDomains(domains);
        ddDeepcrawlerProgressDto.setProgress(progressDto);

        JsonMapper<DdDeepcrawlerProgressDto> jsonMapper = new JsonMapper();
        String input = "";
        try {
            input = jsonMapper.toString(ddDeepcrawlerProgressDto);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        System.out.println(input);

        CrawlJob crawlJob = new CrawlJob.Builder()
                .withJobId(id)
                .withCrawlStatus(Constants.CrawlStatus.STARTED)
                .withWorkspaceId("ws1")
                .withTimestamp(System.currentTimeMillis())
                .withCrawlEntityType(Constants.CrawlEntityType.DD)
                .withCrawlType(Constants.CrawlType.DEEPCRAWL)
                .withNResultsRequested(100)
                .withSources(Lists.newArrayList("DD"))
                .withProgress(false)
                .build();

        when(crawlJobRepository.get(id)).thenReturn(crawlJob);

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, instance, input);

        Thread.sleep(100L);
        verify(ddDeepcrawlerRepository).saveProgress(ddDeepcrawlerProgressDto);
        verify(aquariumClient).fetch(eq(domainDto.getUrl()), any());


    }

}