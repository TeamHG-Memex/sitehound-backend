package com.hyperiongray.sitehound.backend.service.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encoders {
    public static String encodeUrl(String url) throws UnsupportedEncodingException {
        return URLEncoder.encode(url, "UTF-8");
    }

}
