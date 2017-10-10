package com.hyperiongray.sitehound.backend.integration.service.aquarium;

import com.hyperiongray.sitehound.backend.integration.IntegrationTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.test.model.MetadataTestFactory;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DefaultAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DefaultCallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.modeler.input.DdModelerInputService;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfiguration.class})
@ActiveProfiles("integration-test")
@SpringBootTest
@TestPropertySource(locations = {"file:config/properties-override/splash.properties"})
public class AquariumAsyncClientTest {

    @Autowired private AquariumAsyncClient aquariumAsyncClient;
    @Autowired private DefaultAquariumCallbackService defaultAquariumCallbackService;

    @MockBean
    private MongoRepository mongoRepository;
    @MockBean
    private GoogleCrawlerBrokerService googleCrawlerBrokerService;
    @MockBean
    private BingCrawlerBrokerService bingCrawlerBrokerService;
    @MockBean
    private DdModelerInputService ddModelerInputService;
    @MockBean
    private HttpProxyClientImpl httpProxyClient;
    @MockBean
    private HttpClientConnector httpClientConnector;
    @MockBean
    private TikaService tikaService;


    /**
     * Test designed to stress-test splash
     */
    @Test
    public void fetch(){

        Metadata metadataKeywords = MetadataTestFactory.getMetadataKeywords();
        AquariumInput aquariumInput = new AquariumInput(metadataKeywords);
        String targetUrl = "https://google.com";
        aquariumInput.setUrl(targetUrl);
        aquariumInput.setIndex(100);

        DefaultCallbackServiceWrapper callbackServiceWrapper = new DefaultCallbackServiceWrapper(aquariumInput, defaultAquariumCallbackService);

        for (int i = 0; i < 200; i++) {
            aquariumAsyncClient.fetch(targetUrl, callbackServiceWrapper);
        }

        for (int i = 0; i < 100; i++) {
//            System.out.println("sleeping");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}