package com.hyperiongray.sitehound.backend.service.crawler.excavator;

import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.producer.AquariumProducer;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcavatorBrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcavatorBrokerService.class);

    @Autowired private ExcavatorSearchService excavatorSearchService;
    @Autowired private AquariumProducer producer;

    public void process(SubscriberInput subscriberInput) throws IOException {

        LOGGER.info("searching excavator for: " + subscriberInput);
        List<String> onions = excavatorSearchService.search(Sets.newHashSet(subscriberInput.getIncluded()), 0, subscriberInput.getnResults());
        LOGGER.info("got onions: " + onions.size());

        Metadata metadata = buildMetadata(subscriberInput);

        for(String onion : onions){
            AquariumInput aquariumInput = new AquariumInput(metadata);
            aquariumInput.setUrl(onion);
            aquariumInput.setIndex(100);

            producer.submit(aquariumInput);

        }

        LOGGER.info("All excavator results were submitted");
    }


    @NotNull
    private Metadata buildMetadata(SubscriberInput subscriberInput) {
        Metadata metadata = new Metadata();
        metadata.setCrawlEntityType(Constants.CrawlEntityType.TOR);
        metadata.setCrawlType(Constants.CrawlType.KEYWORDS);
//        metadata.setSource(subscriberInput.getSource());
        metadata.setStrTimestamp(subscriberInput.getStrTimestamp());
        metadata.setWorkspace(subscriberInput.getWorkspace());
//        metadata.setTimestamp(subscriberInput.getTimestamp());
//        metadata.setCallbackQueue(subscriberInput.getCallbackQueue());
        metadata.setJobId(subscriberInput.getJobId());
        metadata.setnResults(subscriberInput.getnResults());
        metadata.setKeywordSourceType(subscriberInput.getKeywordSourceType());
        return metadata;
    }
}
