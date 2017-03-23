package com.hyperiongray.sitehound.backend.service.splash;

import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Created by punk on 6/3/15.
 */
@Service
public class Splash {


    @Value( "${aquarium.host}" ) private String splashHost;
    @Value( "${aquarium.url.path}" ) private String splashUrlPath;


    private static final Logger LOGGER = LoggerFactory.getLogger(Splash.class);

    public String snapshot(String targetUrl) throws IOException {

        LOGGER.debug("Getting Snapshot for: " + targetUrl);
        String url = splashHost + splashUrlPath + targetUrl;
        LOGGER.debug("snapshoting: " + url);
        return Request.Get(url)
                       .connectTimeout(90000)
                       .socketTimeout(90000)
                       .execute()
                       .returnContent()
                       .asString();
    }

}