package com.hyperiongray.sitehound.backend.kafka.submitter;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.kafka.producer.AquariumProducer;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by tomas on 20/03/16.
 */

@Deprecated
@Service
public class AquariumTaskSubmitter implements TaskSubmitter {

    @Autowired
    private AquariumProducer producer;

    @Override
    public void submit(Metadata metadata, CrawlResult crawlResult) throws IOException {
        AquariumInput aquariumInput = new AquariumInput(metadata);
        aquariumInput.setUrl(crawlResult.getUrl());
        aquariumInput.setIndex(100);

        producer.submit(aquariumInput);
    }
}
