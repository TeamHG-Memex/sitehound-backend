package com.hyperiongray.sitehound.backend.service.crawler;

import org.junit.Test;

/**
 * Created by tomas on 2/03/16.
 */
public class CrawlerUtilsTest {

    @Test
    public void testGetHostName() throws Exception {
        String hostName = CrawlerUtils.getHostName("http://www.google.com");
        org.junit.Assert.assertEquals(hostName,"google.com");

    }

    @Test
    public void testGetHostName2() throws Exception {
        String hostName = CrawlerUtils.getHostName("http://www2.google.com");
        org.junit.Assert.assertEquals(hostName,"google.com");
    }

    @Test
    public void testGetHostName3() throws Exception {
        String hostName = CrawlerUtils.getHostName("http://mail.google.com");
        org.junit.Assert.assertEquals(hostName,"google.com");
    }

    @Test
    public void testGetHostName4() throws Exception {
        String hostName = CrawlerUtils.getHostName("https://www.google.com");
        org.junit.Assert.assertEquals(hostName,"google.com");
    }

    @Test
    public void testGetHostName5() throws Exception {
        String hostName = CrawlerUtils.getHostName("ftp://www.google.com");
        org.junit.Assert.assertEquals(hostName,"google.com");
    }

    @Test
    public void testGetHostName6() throws Exception {
        String hostName = CrawlerUtils.getHostName("http://www.google.com:80");
        org.junit.Assert.assertEquals(hostName,"google.com");
    }

    @Test
    public void testGetHostNameOnion() throws Exception {
        String hostName = CrawlerUtils.getHostName("ijr246luczc74cgm.onion");
        org.junit.Assert.assertEquals(hostName,"ijr246luczc74cgm.onion");
    }


}