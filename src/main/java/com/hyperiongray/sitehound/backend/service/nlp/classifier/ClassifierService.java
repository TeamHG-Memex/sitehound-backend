package com.hyperiongray.sitehound.backend.service.nlp.classifier;

import com.beust.jcommander.internal.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

/**
 * Created by tomas on 1/03/16.
 */
@Service
public class ClassifierService {

    @Autowired private ClassifierSyncClient classifierSyncClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifierService.class);

    public Set<String> classify(URI uri, String rawHtml){
        try {
            LOGGER.info("Getting classification for: " + uri);

            return classifierSyncClient.get(uri, rawHtml);
        } catch (IOException e) {
            LOGGER.error("ERROR: failed to classify "+ uri, e);
            return Sets.newHashSet();
        }
    }


}
