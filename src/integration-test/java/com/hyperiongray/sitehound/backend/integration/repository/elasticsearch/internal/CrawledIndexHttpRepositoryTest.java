package com.hyperiongray.sitehound.backend.integration.repository.elasticsearch.internal;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.*;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("integration-test")
@TestPropertySource(locations = {"file:config/properties/elasticsearch.properties"})
public class CrawledIndexHttpRepositoryTest {

//    @Autowired private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

    @Autowired private CrawledIndexHttpRepository crawledIndexHttpRepository;

    private JsonMapper<AnalyzedCrawlResultDto> analyzedCrawlResultDtoJsonMapper = new JsonMapper<>();

    @Test
    public void crud() throws Exception {

        String url = "http://www.example.com";
        String html = "<html><head></head><body>.....content...</body></html>";
        String text = "...content...";
        String contentImageDto = "aafadfadfadfadfadfafdadf";
        String title = "my title";

        crawledIndexHttpRepository.delete(url);

        AnalyzedCrawlResultDto analyzedCrawlResultDtoInitial = crawledIndexHttpRepository.get(url);
        Assert.assertNull(analyzedCrawlResultDtoInitial);

        AnalyzedCrawlResultDto analyzedCrawlResultDto = getAnalyzedCrawlResultDto(url, html, contentImageDto, title, text);
        crawledIndexHttpRepository.save(url, analyzedCrawlResultDto);

        crawledIndexHttpRepository.get(url);
    }


    @NotNull
    private AnalyzedCrawlResultDto getAnalyzedCrawlResultDto(String url, String html, String contentImageDto, String title, String text) {
        AnalyzedCrawlResultWrapperDto analyzedCrawlResultWrapperDto = new AnalyzedCrawlResultWrapperDto();
        CrawlResultDto crawlResultDto = new CrawlResultDto(url);
        crawlResultDto.setHtml(html);
        crawlResultDto.setTitle(title);
        crawlResultDto.setImage(new ImageDto(ImageTypeEnum.PNG, contentImageDto));
        crawlResultDto.setTimestamp(System.currentTimeMillis());

        AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);
        analyzedCrawlResultDto.setText(text);
        analyzedCrawlResultWrapperDto.setAnalyzedCrawlResultDto(analyzedCrawlResultDto);
        return analyzedCrawlResultDto;
    }

}