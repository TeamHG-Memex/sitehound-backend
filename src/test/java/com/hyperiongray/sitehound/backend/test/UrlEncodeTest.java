package com.hyperiongray.sitehound.backend.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static junit.framework.TestCase.fail;

public class UrlEncodeTest {

    @Test
    public void test1(){
        String url = "http://www.example.com";
        try {
            String encoded = URLEncoder.encode(url, "UTF-8");
            Assert.assertEquals("http%3A%2F%2Fwww.example.com", encoded);
//            System.out.println(encoded);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test2(){
        String url = "http://www.example.com?afafdafd+adfadf/af";
        try {
            String encoded = URLEncoder.encode(url, "UTF-8");
            Assert.assertEquals("http%3A%2F%2Fwww.example.com%3Fafafdafd%2Badfadf%2Faf", encoded);
//            System.out.println(encoded);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }


}
