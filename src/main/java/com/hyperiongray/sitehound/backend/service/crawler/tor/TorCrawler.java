package com.hyperiongray.sitehound.backend.service.crawler.tor;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.google.common.collect.Lists;
//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestClientFactory;
//import io.searchbox.client.config.HttpClientConfig;
//import io.searchbox.core.Search;
//import io.searchbox.core.SearchResult;
//import io.searchbox.params.SearchType;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 7/27/15.
 */

/**
 * https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html
 */
@Deprecated
@Component
public class TorCrawler implements Crawler<TorCrawlerResult>{

//
//	@Value( "${http.torHost}" ) private String torHost;
//	@Value( "${http.torUser}" ) private String torUser ;
//	@Value( "${http.torPassword}" ) private String torPassword;
//
//	private JestClient client;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(TorCrawler.class);
//
//	@PostConstruct
//	public void postConstruct() throws KeyManagementException, NoSuchAlgorithmException{
//
//		SSLContext sslContext = SSLContext.getInstance("SSL");
//
//		// set up a TrustManager that trusts everything
//		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
//			public X509Certificate[] getAcceptedIssuers() {
//				System.out.println("getAcceptedIssuers =============");
//				return null;
//			}
//
//			public void checkClientTrusted(X509Certificate[] certs,
//			                               String authType) {
//				System.out.println("checkClientTrusted =============");
//			}
//
//			public void checkServerTrusted(X509Certificate[] certs,
//			                               String authType) {
//				System.out.println("checkServerTrusted =============");
//			}
//		} }, new SecureRandom());
//
//		// skip hostname checks
//		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//		JestClientFactory factory = new JestClientFactory();
//
//		DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
//		credentialsProvider.addCredentials(torUser, torPassword);
//
//		//FIXME externalize this
//		factory.setHttpClientConfig(new HttpClientConfig.Builder(torHost)
//				                            .sslSocketFactory(sslSocketFactory)
//				                            .credentialsProvider(credentialsProvider)
//				                            .connTimeout(120 * 1000)
//				                            .readTimeout(120 * 1000)
//											.multiThreaded(true)
//				                            .build());
//		this.client = factory.getObject();
//	}
//
//
	@Override
	public List<TorCrawlerResult> crawl(List<String> included, List<String> excluded, Set<String> irrelevantHosts, Set<String> existentUrl,
	                                    int startingFrom, int pageSize) throws URISyntaxException, IOException{

//		StringBuilder sb = new StringBuilder();
//		sb.append(CrawlerUtils.groupCreator(included));
//		if(excluded.size()>0){
//			sb.append(" NOT " + CrawlerUtils.implodeArray(excluded.toArray(new String[]{}), " NOT "));
//		}
//
//		String query = "{"
////               + "    \"from\":0, \"size\":100,"
////               + "    \"fields\" : [\"url\", \"domain\"],"
//               + "    \"query\": {"
////               + "        \"from\" : 0, \"size\" : 10,"
//               + "        \"filtered\" : {"
//               + "            \"query\" : {"
//               + "                \"query_string\" : {"
//               + "					\"fields\" : [\"title^6\", \"links^2\", \"h1^3\", \"h2^3\", \"text^1\"],"
//               + "                  \"query\" : \""+ sb.toJson() + "\","
//               + "		            \"use_dis_max\" : true"
//               + "                }"
//               + "            },"
//               + "            \"filter\" : {\"exists\" : { \"field\" : \"url\" }"
//               + "            }"
//               + "        }"
//               + "    }"
//               + "}";
//
//
//		Search search = new Search.Builder(query).addIndex("elasticsearch").addType("onions")
//				                .setSearchType(SearchType.QUERY_THEN_FETCH)
//				                .setParameter("from", startingFrom)
//				                .setParameter("size", pageSize)
//				                .build();
//
//		SearchResult result = client.execute(search);
//		LOGGER.debug(result.getJsonString());
//		List<TorCrawlerResult> torCrawlerResults =Lists.newArrayList();
//		Integer resultTotal=result.getTotal();
//		LOGGER.info("total results from tor: " + resultTotal);
//
//		if(resultTotal==0){
//			return torCrawlerResults;
//		}
//
//		if(result.isSucceeded()){
//			List<SearchResult.Hit<TorCrawlerResult, Void>> hits = result.getHits(TorCrawlerResult.class);
//			LOGGER.info("total hits from tor: " + hits.size());
//			for (SearchResult.Hit<TorCrawlerResult, Void> hit: hits) {
//				if(torCrawlerResults.size() >= pageSize){
//					break;
//				}
//				TorCrawlerResult doc = hit.source;
////				LOGGER.info(doc.toJson());
//				torCrawlerResults.add(doc);
//			}
//		}
//		else{
//			String errorMessage=result.getErrorMessage();
//			LOGGER.warn(errorMessage);
//			LOGGER.warn("TOR request failed");
//		}
//		return  torCrawlerResults;
		throw new RuntimeException("unsupported method!");
//        return null;
        //FIXME!!!!

	}

	@Override
	public Constants.CrawlEntityType getCrawlerEntityType(){
		return Constants.CrawlEntityType.TOR;
	}

	@Override
	public int getMaxPageSize(){
		return 10;
	}
}
