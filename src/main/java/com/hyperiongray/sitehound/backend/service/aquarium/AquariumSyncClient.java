package com.hyperiongray.sitehound.backend.service.aquarium;

import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Created by tomas on 9/18/15.
 */
@Deprecated
@Service
public class AquariumSyncClient{

    @Value( "${aquarium.host}" ) private String host;
    @Value( "${aquarium.url.path}" ) private String path;

    private static final Logger LOGGER = LoggerFactory.getLogger(AquariumSyncClient.class);

    public String fetch(String targetUrl) throws IOException {

        LOGGER.debug("Getting Snapshot for: " + targetUrl);
        String url = host + path + targetUrl;
        LOGGER.debug("Taking snapshot of: " + url);

        return Request.Get(url)
                       .connectTimeout(90000)
                       .socketTimeout(90000)
                       .execute()
                       .returnContent()
                       .asString();
    }

}