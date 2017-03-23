package com.hyperiongray.sitehound.backend.service.nlp.classifier;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by tomas on 1/03/16.
 */
public class HttpsTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
    }

}