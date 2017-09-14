package com.hyperiongray.sitehound.backend.test.kafka.deepcrawler;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.DdDeepcrawlerOutputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output.PageSampleDto;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.test.kafka.deepcrawler.producers.DeepcrawlOutputProducer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, KafkaTestConfiguration.class})
public class DeepcrawlerOutputKafkaTemplateTests {

    private static final String TEMPLATE_TOPIC = "dd-deepcrawler-output";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    public DeepcrawlOutputProducer deepcrawlOutputProducer;

    @Test
    public void testTemplate(){

        List<PageSampleDto> pageSamples = new ArrayList<>();

        PageSampleDto pageSampleDto = new PageSampleDto();
        pageSampleDto.setUrl("http://stackexchange.com"); // has to be valid
        pageSampleDto.setDomain("stackexchange-test.com");
        pageSamples.add(pageSampleDto);

        DdDeepcrawlerOutputDto ddDeepcrawlerOutputDto = new DdDeepcrawlerOutputDto();
        ddDeepcrawlerOutputDto.setId("59b0307e166f1c79a63af88a");
        ddDeepcrawlerOutputDto.setPageSamples(pageSamples);

        JsonMapper<DdDeepcrawlerOutputDto> jsonMapper = new JsonMapper();
        String input = "";
        try {
            input = jsonMapper.toString(ddDeepcrawlerOutputDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        deepcrawlOutputProducer.produce(TEMPLATE_TOPIC, embeddedKafka, input);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}