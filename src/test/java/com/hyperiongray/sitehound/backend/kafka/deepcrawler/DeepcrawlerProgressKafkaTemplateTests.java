package com.hyperiongray.sitehound.backend.kafka.deepcrawler;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DdDeepcrawlerProgressDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.DomainDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress.ProgressDto;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.deepcrawler.producers.DeepcrawlerProgressProducer;
import org.assertj.core.util.Lists;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, KafkaTestConfiguration.class})
public class DeepcrawlerProgressKafkaTemplateTests {

    private static final String TEMPLATE_TOPIC = "dd-deepcrawler-progress";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private DeepcrawlerProgressProducer deepcrawlerProgressProducer;

    @Test
    public void testTemplate() throws Exception {

        DdDeepcrawlerProgressDto ddDeepcrawlerProgressDto = new DdDeepcrawlerProgressDto();
        ddDeepcrawlerProgressDto.setId("59a18279166f1c48c18760c5");

        ProgressDto progressDto = new ProgressDto();
        progressDto.setPagesFetched(1000);
        progressDto.setRpm(1000);
        progressDto.setPagesFetched(155565);
        progressDto.setStatus("running");
        List<DomainDto> domains = Lists.newArrayList();

        DomainDto domainDto = new DomainDto();
        domainDto.setDomain("domain1");
        domainDto.setPagesFetched(1999);
        domainDto.setRpm(100);
        domainDto.setStatus("started");
        domainDto.setUrl("http://stackexchange.com");
        domains.add(domainDto);
        progressDto.setDomains(domains);
        ddDeepcrawlerProgressDto.setProgress(progressDto);

        JsonMapper<DdDeepcrawlerProgressDto> jsonMapper = new JsonMapper();
        String input = "";
        try {
            input = jsonMapper.toString(ddDeepcrawlerProgressDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(input);
/*
        String input = "{" +
                "\"id\": \"59a18279166f1c48c18760c5\"," +
                "\"progress\": {" +
                "\"status\": \"running\"," +
                "\"pages_fetched\": 1468," +
                "\"rpm\": 24000," +
                "\"domains\": [" +
                " {\"url\":\"http://example.com\", \"domain\": \"example.com\", \"pages_fetched\": 1234, \"status\": \"finished\", \"rpm\":12000}," +
                " {\"url\":\"http://google.com\", \"domain\": \"google.com\", \"pages_fetched\": 234, \"status\": \"finished\", \"rpm\":12000}" +
                "]"+
                "}" +
        "}";
*/
        deepcrawlerProgressProducer.produce(TEMPLATE_TOPIC, embeddedKafka, input);
    }

}