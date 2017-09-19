package com.hyperiongray.sitehound.backend.service.aquarium;

import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.model.MetadataTestFactory;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.service.impl.DefaultAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.DefaultCallbackServiceWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@SpringBootTest
public class AquariumAsyncClientTest {

    @Autowired private AquariumAsyncClient aquariumAsyncClient;
    @Autowired private DefaultAquariumCallbackService defaultAquariumCallbackService;
//    @Autowired private MetadataBuilder metadataBuilder;


    /**
     * Test designed to stress splash
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
                Thread.currentThread().sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}