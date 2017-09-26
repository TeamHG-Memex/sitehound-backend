package com.hyperiongray.sitehound.backend.kafka.crawler;

import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.Producer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output.DdCrawlerOutputProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.CrawlJobRepository;
import com.hyperiongray.sitehound.backend.service.dd.crawler.output.DdCrawlerOutputProgressBrokerService;
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

    @Test
    public void testTemplate() {

        String workspaceId = "59b114a2e4dc96629bb2b2fb";
        String jobId = "555114a2e4dc9662e4dc9662";
        Double percentageDone = 98.123;

        String json = "{" +
                "    \"id\": \"" + jobId + "\"," +
                "    \"workspace_id\": \""+ workspaceId +"\"," +
                "    \"progress\": \"Crawled N pages and M domains, average reward is 0.122\"," +
                "    \"percentage_done\": "+ percentageDone +
                "}";

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, brokerService, json);

        // expecting repo to be called once with correct param
        verify(crawlJobRepositoryMock).saveProgress(jobId, percentageDone);

        ArgumentCaptor<String> jobIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> percentageDoneCaptor = ArgumentCaptor.forClass(Double.class);
        verify(crawlJobRepositoryMock).saveProgress(jobIdCaptor.capture(), percentageDoneCaptor.capture());
        assertEquals(jobId, jobIdCaptor.getValue());
        assertEquals(percentageDone, percentageDoneCaptor.getValue());

        DdCrawlerOutputProgress ddCrawlerOutputProgress = new DdCrawlerOutputProgress();
        ddCrawlerOutputProgress.setWorkspaceId(workspaceId);
        ddCrawlerOutputProgress.setId(jobId);
        ddCrawlerOutputProgress.setPercentageDone(percentageDone);
        String progress = "Crawled N pages and M domains, average reward is 0.122";
        ddCrawlerOutputProgress.setProgress(progress);


        ArgumentCaptor<DdCrawlerOutputProgress> argument = ArgumentCaptor.forClass(DdCrawlerOutputProgress.class);
        verify(crawlJobRepositoryMock).saveProgress(argument.capture());
        assertEquals(jobId, argument.getValue().getId());
        assertEquals(workspaceId, argument.getValue().getWorkspaceId());
        assertEquals(progress, argument.getValue().getProgress());
        assertEquals(percentageDone, argument.getValue().getPercentageDone());

    }
}