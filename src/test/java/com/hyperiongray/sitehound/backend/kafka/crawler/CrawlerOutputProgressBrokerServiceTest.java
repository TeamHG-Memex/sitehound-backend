package com.hyperiongray.sitehound.backend.kafka.crawler;

import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.test.kafka.Producer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.crawler.output.DdCrawlerOutputProgressBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.modeler.input.DdModelerInputService;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@SpringBootTest
public class CrawlerOutputProgressBrokerServiceTest {

    private static final String TEMPLATE_TOPIC = "dd-crawler-output-pages";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private Producer producer;

    @Autowired private DdCrawlerOutputProgressBrokerService brokerService;

    @MockBean private CrawlJobRepository crawlJobRepositoryMock;

    @MockBean AquariumAsyncClient aquariumAsyncClient;
    @MockBean MongoRepository mongoRepository;
    @MockBean GoogleCrawlerBrokerService googleCrawlerBrokerService;
    @MockBean BingCrawlerBrokerService bingCrawlerBrokerService;
    @MockBean DdModelerInputService ddModelerInputService;
    @MockBean HttpProxyClientImpl httpProxyClient;
    @MockBean HttpClientConnector httpClientConnector;
    @MockBean TikaService tikaService;



    @Test
    public void testTemplate() {

        String workspaceId = "59b114a2e4dc96629bb2b2fb";
        String jobId = "555114a2e4dc9662e4dc9662";
        Double percentageDone = 98.123;
        String progress = "Crawled N pages and M domains, average reward is 0.122";

        String json = "{" +
                "    \"id\": \"" + jobId + "\"," +
                "    \"workspace_id\": \""+ workspaceId +"\"," +
                "    \"progress\": \""+progress+"\"," +
                "    \"percentage_done\": "+ percentageDone +
                "}";

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, brokerService, json);

        DdCrawlerOutputProgress ddCrawlerOutputProgress = new DdCrawlerOutputProgress();
        ddCrawlerOutputProgress.setWorkspaceId(workspaceId);
        ddCrawlerOutputProgress.setId(jobId);
        ddCrawlerOutputProgress.setProgress(progress);
        ddCrawlerOutputProgress.setPercentageDone(percentageDone);

        ArgumentCaptor<DdCrawlerOutputProgress> argument = ArgumentCaptor.forClass(DdCrawlerOutputProgress.class);
        verify(crawlJobRepositoryMock).saveProgress(argument.capture());
        assertEquals(jobId, argument.getValue().getId());
        assertEquals(workspaceId, argument.getValue().getWorkspaceId());
        assertEquals(progress, argument.getValue().getProgress());
        assertEquals(percentageDone, argument.getValue().getPercentageDone());

    }
}