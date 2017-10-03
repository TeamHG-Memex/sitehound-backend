package com.hyperiongray.sitehound.backend.service.nlp.classifier;

import com.hyperiongray.sitehound.backend.service.nlp.classifier.api.ClassifierInput;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.service.nlp.classifier.api.ClassifierOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

/**
 * Created by tomas on 1/03/16.
 */
@Component
public class ClassifierSyncClient {

	@Value( "${classifier.host}" ) private String host;
	@Value( "${classifier.host.url}" ) private String hostUrl;
	@Value( "${classifier.port}" ) private String port;
	@Value( "${classifier.url.path}" ) private String path;
	@Value( "${classifier.user}" ) private String user;
	@Value( "${classifier.password}" ) private String password;


    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifierSyncClient.class);
	private RequestConfig config;
	private CloseableHttpClient httpClient;
	private JsonMapper<Set<String>> jsonMapper = new JsonMapper();

	@PostConstruct
	public void postConstruct() throws KeyManagementException, NoSuchAlgorithmException {
//		SSLContext sslContext = SSLContexts.custom().useSSL().build();
//		sslContext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
//
//		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//
//		SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//
//		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//		credentialsProvider.setCredentials(new AuthScope(host, AuthScope.ANY_PORT),
//				new UsernamePasswordCredentials(user, password));

		httpClient = HttpClients.custom()
//				.setSSLSocketFactory(factory)
//				.setSslcontext(sslContext)
//				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//				.setDefaultCredentialsProvider(credentialsProvider)
				.setMaxConnPerRoute(100)
				.setMaxConnTotal(101)
				.build();
	}

	public Set<String> get(URI targetUrl, String rawHtml) throws IOException {

		try {

			String content =  new JsonMapper().toString(new ClassifierInput(rawHtml));

			String url = hostUrl +":"+ port + path + URLEncoder.encode(targetUrl.toString(), "UTF-8");
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(config);
            httpPost.setHeader("User-Agent", "sitehound-backend");
            httpPost.setHeader("Content-Type", "application/json");

//			httpPost.setEntity(new StringEntity("{\"html\":\"" + rawHtml + "\"}", ContentType.APPLICATION_JSON));
			httpPost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));


			LOGGER.info("Executing request " + httpPost.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(final HttpResponse response) throws IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity, Charset.forName("UTF-8")) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};

			String responseBody = httpClient.execute(httpPost, responseHandler);
            LOGGER.info(responseBody);
			ClassifierOutput classifierOutput = new ObjectMapper().readValue(responseBody, new TypeReference<ClassifierOutput>() {});
			if(classifierOutput == null){
				LOGGER.error("COULD NOT CLASSIFY: " + targetUrl);
				classifierOutput = new ClassifierOutput();
			}
			else{
				classifierOutput.getCategories().remove("UNDEFINED");
			}

            return classifierOutput.getCategories();

		} finally {
//			httpClient.close();
		}


	}
}
