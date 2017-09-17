package com.hyperiongray.sitehound.backend.kafka.trainer;

import com.google.common.collect.Lists;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ImageTypeEnum;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.GenericCrawlMongoRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DdTrainerOutputPagesAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DdTrainerOutputPagesCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.MetadataBuilder;
import com.hyperiongray.sitehound.backend.service.dd.trainer.output.DdTrainerOutputPagesBrokerService;
import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.Producer;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@SpringBootTest
public class TrainerOutputPagesBrokerServiceTest {


    private static final String TEMPLATE_TOPIC = "dd-trainer-output-pages";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private DdTrainerOutputPagesBrokerService brokerService;

    @Autowired
    private Producer producer;

    @MockBean
    private AquariumAsyncClient aquariumClient;

    @MockBean private MetadataBuilder metadataBuilder;
    @MockBean private CrawledIndexHttpRepository analyzedCrawlResultDtoIndexRepositoryMock;
    @MockBean private GenericCrawlMongoRepository genericCrawlMongoRepositoryMock;

    //for...
    @Autowired private DdTrainerOutputPagesAquariumCallbackService ddTrainerOutputPagesAquariumCallbackService;

    @Test
    public void testTemplate() {

        String workspaceId = "59b114a2e4dc96629bb2b2fb";
        String url1 = "http://example.com";
        String url2 = "http://example.com";
        String domain1 = "example1.com";
        String domain2 = "example2.com";
        Double score1 = 80.0;
        Double score2 = 90.0;
        Long timestamp = System.currentTimeMillis();

        String json = "{" +
                        "\"workspace_id\": \"" + workspaceId + "\"," +
                        "\"page_samples\": [" +
                                "{\"url\": \"" + url1 + "\", \"domain\": \"" + domain1 + "\", \"score\": " + score1 + "}" +
//                                ",{\"url\": \"" + url2 + "\", \"domain\": \"" + domain2 + "\", \"score\": " + score2 + "}" +
                            "]" +
                        "}";

//        Metadata metadataMock = metadataBuilder.buildFromTrainerOutputPages(workspaceId);
        Metadata metadataMock = new Metadata();
        metadataMock.setWorkspace(workspaceId);
        metadataMock.setJobId(workspaceId); // using the workspace as jobId cause not only one job per ws is allowed
        metadataMock.setCrawlType(Constants.CrawlType.KEYWORDS);
        metadataMock.setSource("DD");
        metadataMock.setStrTimestamp(String.valueOf(timestamp));
        metadataMock.setTimestamp(timestamp);
        metadataMock.setCallbackQueue("");
        metadataMock.setCrawlEntityType(Constants.CrawlEntityType.DD);
        metadataMock.setnResults(30);

        when(metadataBuilder.buildFromTrainerOutputPages(workspaceId)).thenReturn(metadataMock);

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, brokerService, json);

//
//        try {
//            Thread.sleep(2*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Thread.sleep(2*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
        PageSample pageSample = new PageSample();
        pageSample.setDomain(domain1);
        pageSample.setScore(score1);
        pageSample.setUrl(url1);
//        DdTrainerOutputPagesCallbackServiceWrapper callbackServiceWrapperMock = new DdTrainerOutputPagesCallbackServiceWrapper(pageSample, metadataMock, ddTrainerOutputPagesAquariumCallbackService);

        verify(aquariumClient).fetch(eq(url1), any());


        /*
        // update ES index
        String hashKey = analyzedCrawlResultDtoIndexRepository.upsert(pageSample.getUrl(), crawlRequestDto.getWorkspace(), crawlRequestDto.getCrawlEntityType(), analyzedCrawlResultDto);
        */

        try {
            when(analyzedCrawlResultDtoIndexRepositoryMock.upsert(anyString(), anyString(), any(), any())).thenReturn(url1);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        manually invoke the callback to mock the splash call
        String html = "<DOCTYPE><html>....</html>";
        String png = "4435425245";
        String title = "Example Domain";
        ArrayList<Integer> geometry = Lists.newArrayList(1, 2, 3);


        DdTrainerOutputPagesCallbackServiceWrapper ddTrainerOutputPagesCallbackServiceWrapper =
                new DdTrainerOutputPagesCallbackServiceWrapper(pageSample, metadataMock, ddTrainerOutputPagesAquariumCallbackService);

        AquariumInternal aquariumInternal = new AquariumInternal();
        aquariumInternal.setGeometry(geometry);
        aquariumInternal.setHtml(html);
        aquariumInternal.setPng(png);
        aquariumInternal.setRequestedUrl(url1);
        aquariumInternal.setTitle(title);

        ddTrainerOutputPagesCallbackServiceWrapper.execute(url1, aquariumInternal);


        ArgumentCaptor<String> capturedHashKey = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> capturedWorkspaceId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Constants.CrawlEntityType> capturedCrawlEntityType = ArgumentCaptor.forClass(Constants.CrawlEntityType.class);
        ArgumentCaptor<AnalyzedCrawlResultDto> capturedAnalyzedCrawlResultDto = ArgumentCaptor.forClass(AnalyzedCrawlResultDto.class);

//        ArgumentCaptor<DdLoginInput> argument = ArgumentCaptor.forClass(DdLoginInput.class);
//        verify(keywordsAquariumCallbackServiceMock).save(argument.capture());
        try {
            verify(analyzedCrawlResultDtoIndexRepositoryMock).upsert(capturedHashKey.capture(), capturedWorkspaceId.capture(), capturedCrawlEntityType.capture(), capturedAnalyzedCrawlResultDto.capture());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        assertEquals(ddLoginInput, argument.getValue());
        assertEquals(pageSample.getUrl(), capturedHashKey.getValue());
        assertEquals(workspaceId, capturedWorkspaceId.getValue());
        assertEquals(Constants.CrawlEntityType.DD, capturedCrawlEntityType.getValue());
        assertEquals(title, capturedAnalyzedCrawlResultDto.getValue().getCrawlResultDto().getTitle());
        assertEquals(url1, capturedAnalyzedCrawlResultDto.getValue().getCrawlResultDto().getUrl());
        assertEquals(ImageTypeEnum.PNG, capturedAnalyzedCrawlResultDto.getValue().getCrawlResultDto().getImage().getType());
//        assertEquals(Constants.CrawlEntityType.DD, capturedAnalyzedCrawlResultDto.getValue().getCrawlResultDto().getHost());
//        assertEquals(Constants.CrawlEntityType.DD, capturedAnalyzedCrawlResultDto.getValue().getCrawlResultDto().getTimestamp());
//        assertEquals(Constants.CrawlEntityType.DD, capturedAnalyzedCrawlResultDto.getValue().getCrawlResultDto().getHtml());


/*
        // update mongodb
        Map<String, Object> document = defaultCrawlContextDtoTranslator.translate(hashKey, crawlRequestDto, analyzedCrawlResultDto, pageSample.getScore());
        genericCrawlMongoRepository.save(crawlRequestDto.getCrawlType(), crawlRequestDto.getWorkspace(), document);
*/


        ArgumentCaptor<Constants.CrawlType> capturedCrawlType = ArgumentCaptor.forClass(Constants.CrawlType.class);
        ArgumentCaptor<String> capturedWorkspaceId2 = ArgumentCaptor.forClass(String.class);
        Class<Map<String, Object>> capturedDocumentClass = (Class<Map<String, Object>>)(Class)Map.class;
        ArgumentCaptor<Map<String, Object>> capturedDocument = ArgumentCaptor.forClass(capturedDocumentClass);

        verify(genericCrawlMongoRepositoryMock).save(capturedCrawlType.capture(), capturedWorkspaceId2.capture(), capturedDocument.capture());

        assertEquals(workspaceId, capturedWorkspaceId2.getValue());
        assertEquals(Constants.CrawlType.KEYWORDS, capturedCrawlType.getValue());

        Map<String, Object> capturedDocumentValue = capturedDocument.getValue();
        assertEquals(url1, capturedDocumentValue.get("hashKey"));
//        assertEquals(List.empty(), capturedDocumentValue.get("words"));
        assertEquals(title, capturedDocumentValue.get("title"));
        assertEquals(url1, capturedDocumentValue.get("url"));
        assertEquals(workspaceId, capturedDocumentValue.get("jobId"));
        assertEquals(workspaceId, capturedDocumentValue.get("workspaceId"));
        assertEquals(score1, capturedDocumentValue.get("score"));
        assertEquals(Constants.CrawlerProvider.HH_JOOGLE.name(), capturedDocumentValue.get("provider"));
        assertEquals("example.com", capturedDocumentValue.get("host"));
        assertEquals(Constants.CrawlEntityType.DD.name(), capturedDocumentValue.get("crawlEntityType"));
//        assertEquals(List.empty(), capturedDocumentValue.get("categories"));
        assertEquals(timestamp, capturedDocumentValue.get("timestamp"));

        System.out.println("done");
    }


}
