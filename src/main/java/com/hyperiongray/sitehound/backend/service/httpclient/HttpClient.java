package com.hyperiongray.sitehound.backend.service.httpclient;

import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tomas on 7/23/15.
 */

public interface HttpClient {

	org.apache.http.client.HttpClient getClient();

	String fetch(URI uri) throws IOException;

	void init() throws KeyManagementException, NoSuchAlgorithmException;
}
