package com.hyperiongray.sitehound.backend.service.crawler.excavator;

import com.beust.jcommander.internal.Lists;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class ExcavatorSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcavatorSearchService.class);

    @Value( "${excavator.scheme}" ) private String scheme;
    @Value( "${excavator.host}" ) private String host;
    @Value( "${excavator.port}" ) private int port;
    @Value( "${excavator.index}" ) private String index;
    @Value( "${excavator.user}" ) private String user;
    @Value( "${excavator.password}" ) private String password;

    private RestHighLevelClient client;

    @PostConstruct
    public void postConstruct(){

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));


        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");

            // set up a TrustManager that trusts everything
            sslContext.init(null, new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    System.out.println("getAcceptedIssuers =============");
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs,
                                               String authType) {
                    System.out.println("checkClientTrusted =============");
                }

                public void checkServerTrusted(X509Certificate[] certs,
                                               String authType) {
                    System.out.println("checkServerTrusted =============");
                }
            } }, new SecureRandom());

            // skip hostname checks
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);

            RestClient lowLevelRestClient = RestClient.builder(new HttpHost(host, port, scheme))
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                                    .setSSLContext(sslContext)
                                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                    .setUserAgent("SITEHOUND-BACKEND")
                                    .setMaxConnTotal(20)
                                    .setMaxConnPerRoute(20)
//                                    .readTimeout(120 * 1000)
//                                    setcconnTimeout(120 * 1000)
//                                    .multiThreaded(true)
                                    ;
                        }
                    })
                    .build();

            client = new RestHighLevelClient(lowLevelRestClient);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        //TODO   restClient.close(); when the app closes!
    }


    public List<String> search(Set<String> keywords, int startingFrom, int pageSize) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append(CrawlerUtils.groupCreator(Lists.newArrayList(keywords)));

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types("pages");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

//        TermQueryBuilder termScreenshot = QueryBuilders.termQuery("screenshot","true").boost(2f);
//        boolQueryBuilder.filter(termScreenshot);

        for(String keyword : keywords){
            TermQueryBuilder term1 = QueryBuilders.termQuery("pagetitle", keyword).boost(4f);
            TermQueryBuilder term2 = QueryBuilders.termQuery("url", keyword).boost(3f);
            TermQueryBuilder term3 = QueryBuilders.termQuery("pagetext", keyword).boost(1f);
            boolQueryBuilder.should(term1).should(term2).should(term3);
        }

        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.from(startingFrom);
        sourceBuilder.size(pageSize);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();

        List<String> onions = Lists.newArrayList();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit);
            onions.add((String) hit.getSourceAsMap().get("url"));
        }

        return onions;
    }

    /*
    @PostConstruct
    public void postConstruct() throws KeyManagementException, NoSuchAlgorithmException {

        SSLContext sslContext = SSLContext.getInstance("SSL");

        // set up a TrustManager that trusts everything
        sslContext.init(null, new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers =============");
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                System.out.println("checkClientTrusted =============");
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                System.out.println("checkServerTrusted =============");
            }
        } }, new SecureRandom());

        // skip hostname checks
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        JestClientFactory factory = new JestClientFactory();

        DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
        credentialsProvider.addCredentials(user, password);

        //TODO externalize this
        factory.setHttpClientConfig(new HttpClientConfig.Builder(endpoint)
                .sslSocketFactory(sslSocketFactory)
                .credentialsProvider(credentialsProvider)
                .connTimeout(120 * 1000)
                .readTimeout(120 * 1000)
                .multiThreaded(true)
                .build());
        this.client = factory.getObject();
    }

    public void search(Set<String> keywords, int startingFrom, int pageSize){

        StringBuilder sb = new StringBuilder();
        sb.append(CrawlerUtils.groupCreator(Lists.newArrayList(keywords)));

        String query = "{"
                + "    \"query\": {"
                + "        \"filtered\" : {"
                + "            \"query\" : {"
                + "                \"query_string\" : {"
                + "                \"fields\" : [\"pagetitle^6\", \"url^3\", \"pagetext^2\"],"
                + "                  \"query\" : \""+ sb.toString() + "\"" //+ "\","
//                + "		            \"use_dis_max\" : true"
                + "                }"
                + "            },"
                + "            \"filter\" : {\"exists\" : { \"field\" : \"url\" }"
                + "            }"
                + "        }"
                + "    }"
                + "}";


        LOGGER.info("excavator query: " + query);
        Search search = new Search.Builder(query).addIndex("deeptexts").addType("pages")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setParameter("from", startingFrom)
                .setParameter("size", pageSize)
                .build();

        SearchResult result = null;
        try {
            result = client.execute(search);
            System.out.println(result.getTotal());
            System.out.println(result.getMaxScore());
        } catch (IOException e) {
            LOGGER.error("Request to excavator Failed!", e);
        }
        LOGGER.debug(result.getJsonString());

        //        return null;


    }
*/

}
