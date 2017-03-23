package com.hyperiongray.sitehound.backend.service.nlp.classifier;

import org.springframework.stereotype.Component;

/**
 * Created by tomas on 1/03/16.
 */
@Component
public class ClassifierAsyncClient {
    /*
    @Value( "${classifier.host}" ) private String host;
    @Value( "${classifier.url.path}" ) private String path;
    @Value( "${classifier.threads}" ) private int threads;

    private FutureRequestExecutionService futureRequestExecutionService;
    private RequestConfig config;
    private ExecutorService executorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifierAsyncClient.class);

    @PostConstruct
    public void postConstruct(){
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(100).setMaxConnTotal(threads-1).build();
        executorService= Executors.newFixedThreadPool(threads);
        futureRequestExecutionService = new FutureRequestExecutionService(httpClient, executorService);
        config = RequestConfig.custom().setConnectionRequestTimeout(120*1000).setConnectTimeout(120*1000).setSocketTimeout(120*1000).build();

    }

    public void get(URI targetUrl, String rawHtml, FutureCallback<Content> classifierCallback) throws IOException {

        ResponseHandler<Content> handler = new ContentResponseHandler();
        LOGGER.debug("Getting classification for: " + targetUrl);
        String url = host + path + targetUrl;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        futureRequestExecutionService.execute(httpGet, HttpClientContext.create(), handler, classifierCallback);
        LOGGER.debug("Scheduled async classification for: " + url);

//        Request request=Request.Get(url).connectTimeout(90000).socketTimeout(90000);
//        Async.newInstance().use(executorService).execute(request, handler, myCallback);

    }


*/
}
