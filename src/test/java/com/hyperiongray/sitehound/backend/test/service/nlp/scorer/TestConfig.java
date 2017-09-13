package com.hyperiongray.sitehound.backend.test.service.nlp.scorer;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tomas on 17/03/16.
 */
@Configuration
public class TestConfig {

//    @Bean
//    public CrawledIndexService mockCrawledIndexService(){
//        return new MockCrawledIndexService();
//    }
//
    @Value("classpath:html/books.html")
    private Resource booksHtml;

    @Bean
    @Qualifier("books")
    public String booksHtmlData() throws IOException {
        try(InputStream is = booksHtml.getInputStream()) {
            return IOUtils.toString(is);
        }
    }


    @Value("classpath:html/arabic-cnn.html")
    private Resource arabicCnnHtml;

    @Bean
    @Qualifier("arabicCnn")
    public String arabicCnnHtmlData() throws IOException {
        try(InputStream is = arabicCnnHtml.getInputStream()) {
            return IOUtils.toString(is);
        }
    }


    @Value("classpath:html/other.html")
    private Resource otherHtml;

    @Bean
    @Qualifier("other")
    public String otherHtmlData() throws IOException {
        try(InputStream is = otherHtml.getInputStream()) {
            return IOUtils.toString(is);
        }
    }

    @Value("classpath:html/alquds.html")
    private Resource alqudsHtml;

    @Bean
    @Qualifier("alquds")
    public String alqudsHtmlData() throws IOException {
        try(InputStream is = alqudsHtml.getInputStream()) {
            return IOUtils.toString(is);
        }
    }

    @Value("classpath:html/broadcastschedule.html")
    private Resource broadcastschedule;

    @Bean
    @Qualifier("broadcastschedule")
    public String broadcastscheduleHtmlData() throws IOException {
        try(InputStream is = broadcastschedule.getInputStream()) {
            return IOUtils.toString(is);
        }
    }


}