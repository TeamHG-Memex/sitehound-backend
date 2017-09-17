package com.hyperiongray.sitehound.backend.service.aquarium;

import com.hyperiongray.sitehound.backend.service.aquarium.callback.wrapper.CallbackServiceWrapper;
import com.hyperiongray.sitehound.backend.service.aquarium.clientCallback.AquariumAsyncClientCallback;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.ContentResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * Created by tomas on 9/18/15.
 */
@Service
public class AquariumAsyncClient{
    private static final Logger LOGGER = LoggerFactory.getLogger(AquariumAsyncClient.class);

    @Value( "${aquarium.host}" ) private String host;
    @Value( "${aquarium.url.path}" ) private String path;
    @Value( "${aquarium.threads}" ) private int threads;
    @Value( "${aquarium.user}" ) private String user;
    @Value( "${aquarium.password}" ) private String password;

    private FutureRequestExecutionService futureRequestExecutionService;
    private RequestConfig config;
    private ExecutorService executorService;
    private Semaphore semaphore;

    @PostConstruct
    public void postConstruct(){
        semaphore = new Semaphore(threads);

        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, password);
        provider.setCredentials(AuthScope.ANY, credentials);

//        HttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(100).setMaxConnTotal(threads-1).build();
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(threads-1).setMaxConnTotal(threads-1).setDefaultCredentialsProvider(provider).build();
        executorService=Executors.newFixedThreadPool(threads);
        futureRequestExecutionService = new FutureRequestExecutionService(httpClient, executorService);
        config = RequestConfig.custom().setConnectionRequestTimeout(120*1000).setConnectTimeout(120*1000).setSocketTimeout(120*1000).build();

	    //TODO implement retry strategy ...http://fahdshariff.blogspot.com.es/2009/08/retrying-operations-in-java.html ?
    }


    public void fetch(String targetUrl, CallbackServiceWrapper callbackServiceWrapper) {
        fetch(targetUrl, new ContentResponseHandler(), callbackServiceWrapper);
    }

//    public void fetch(String targetUrl,  ResponseHandler<Content> handler, FutureCallback<Content> myCallback) throws IOException {
    private void fetch(String targetUrl, ResponseHandler<Content> handler, CallbackServiceWrapper callbackServiceWrapper){
        try{
            LOGGER.info("splash available semaphores: " + semaphore.availablePermits());
            LOGGER.debug("Getting Snapshot for: " + targetUrl);
            AquariumAsyncClientCallback callback = new AquariumAsyncClientCallback(targetUrl, semaphore, callbackServiceWrapper);
            String url = host + path + targetUrl;
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(config);
            futureRequestExecutionService.execute(httpGet, HttpClientContext.create(), handler, callback);
            LOGGER.debug("Scheduled snapshot of: " + url);
        }
        catch (Exception ex){
            LOGGER.warn("Failed Scheduled snapshot of: " + targetUrl);
            semaphore.release();
        }
    }

}