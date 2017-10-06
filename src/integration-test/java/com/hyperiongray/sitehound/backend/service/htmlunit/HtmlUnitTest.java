package com.hyperiongray.sitehound.backend.service.htmlunit;

//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import junit.framework.Assert;

//import com.gargoylesoftware.htmlunit.Page;
//import com.gargoylesoftware.htmlunit.Page;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import org.junit.Assert;
//
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;


// alternatively define assertEquals as static import
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.net.URL;

//import com.gargoylesoftware.htmlunit.Page;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import org.junit.Assert;
//import org.junit.Test;

/**
 * Created by tomas on 5/23/15.
 */
@ActiveProfiles("integration-test")
public class HtmlUnitTest {


	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUnitTest.class);

	@Test
	public void homePage() throws Exception {
		final WebClient webClient = new WebClient();
		final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
		Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

		final String pageAsXml = page.asXml();
		Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

		final String pageAsText = page.asText();
		Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	}

	@Test
	@Ignore
	/**
	 * Test getting a page protected under auth
	 */
	public void homePage2() throws Exception {

		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_31);

		//auth
		DefaultCredentialsProvider provider = new DefaultCredentialsProvider();
		Credentials credentials = new UsernamePasswordCredentials("admin", "memexpass");
		AuthScope authScope = AuthScope.ANY;
		provider.setCredentials(authScope, credentials);
		webClient.setCredentialsProvider(provider);


		WebRequest request = new WebRequest(new URL("http://localhost:5000/seed-url"));
		final HtmlPage page = webClient.getPage(request);
		JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();
		while (manager.getJobCount() > 0) {
			Thread.sleep(1000);
		}

		LOGGER.info(page.asText());

	}
}
