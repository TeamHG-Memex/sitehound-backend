package com.hyperiongray.sitehound.backend.service.htmlunit;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by tomas on 5/24/15.
 */
@Service
public class UrlFetcher {
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlFetcher.class);

	@Autowired
	private HttpClientConnector httpClientConnector;

	public String fetch(String url) throws IOException, InterruptedException, FailingHttpStatusCodeException {
		LOGGER.info("Fetching: " + url);

		long start = System.currentTimeMillis();
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_31);

		//Important: enable this to use proxy for each fetch
		webClient.setWebConnection(new HttpClientBackedWebConnection(webClient, httpClientConnector.getClient()));

		WebRequest request = new WebRequest(new URL(url));
		request.setCharset("UTF-8");
		webClient.setJavaScriptTimeout(15000);
		webClient.waitForBackgroundJavaScript(10000);
//		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setTimeout(60 * 1000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setAppletEnabled(false);
		webClient.getOptions().setGeolocationEnabled(false);
		webClient.getOptions().setPopupBlockerEnabled(true);
		HtmlPage page = webClient.getPage(request);

		String content = page.asText();
		// clean up
		try {
			final List<WebWindow> windows = webClient.getWebWindows();
			for (final WebWindow wd : windows) {
				wd.getJobManager().removeAllJobs();
			}
			webClient.waitForBackgroundJavaScript(0);
			webClient.getCache().clear();
			webClient.getCookieManager().clearCookies();
			webClient.getJavaScriptEngine().shutdown();
			webClient.close();
			webClient = null;
			request = null;
			page = null;
		} catch (Exception e){
			LOGGER.warn("failed to cleanup HtmlUnit",e);
		}

		LOGGER.info("Fetched: " + url + ". Took: "  + ((System.currentTimeMillis() - start)) + " millis");
		return content;
	}

}
