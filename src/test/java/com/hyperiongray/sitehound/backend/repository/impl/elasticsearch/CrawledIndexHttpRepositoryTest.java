package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElasticsearchTestConfiguration.class})
@SpringBootTest
public class CrawledIndexHttpRepositoryTest {

    @Autowired private CrawledIndexHttpRepository crawledIndexHttpRepository;

    @Test
    public void save() throws Exception {
        fail();
//
//        String url = "http://www.example.com";
//        crawledIndexHttpRepository.save(url);
    }

    @Test
    public void upsert() throws Exception {
        fail();
    }

    @Test
    public void get() throws Exception {
        fail();
    }

    @Test
    public void delete() throws Exception {
        fail();
    }

}