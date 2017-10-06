package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultWrapperDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository.CRAWLED_INDEX_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository.CRAWLED_TYPE_NAME;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElasticsearchTestConfiguration.class})
@SpringBootTest
public class CrawledIndexHttpRepositoryTest {

    @Autowired private CrawledIndexHttpRepository instance;

    @MockBean private com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchRepositoryTemplate elasticsearchRepositoryTemplateMock;

    private static final String workspaceId = "ws123456";
    private static final String url = "http://www.example.com";

    @Test
    public void save() throws Exception {
//        String workspaceId = "ws1111";
        CrawlResultDto crawlResultDto = new CrawlResultDto(url);
        AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);
        instance.save(url, workspaceId, Constants.CrawlEntityType.DD, analyzedCrawlResultDto);


        ArgumentCaptor<String> indexNameArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<AnalyzedCrawlResultDto> analyzedCrawlResultDtoArgument = ArgumentCaptor.forClass(AnalyzedCrawlResultDto.class);
        verify(elasticsearchRepositoryTemplateMock).save(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture(), analyzedCrawlResultDtoArgument.capture());

        assertEquals(CRAWLED_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(CRAWLED_TYPE_NAME, typeArgument.getValue());
        assertEquals(url, idArgument.getValue());

        AnalyzedCrawlResultWrapperDto analyzedCrawlResultWrapperDto = new AnalyzedCrawlResultWrapperDto();
        analyzedCrawlResultWrapperDto.setResult(analyzedCrawlResultDto);
        analyzedCrawlResultWrapperDto.setWorkspaces(Sets.<String>newHashSet(workspaceId));

        assertEquals(analyzedCrawlResultWrapperDto, analyzedCrawlResultDtoArgument.getValue());

    }

    @Test
    public void get() throws Exception {
        ArgumentCaptor<String> indexNameArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idArgument = ArgumentCaptor.forClass(String.class);

        instance.get(url);

        verify(elasticsearchRepositoryTemplateMock).get(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture(), any());
        assertEquals(CRAWLED_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(CRAWLED_TYPE_NAME, typeArgument.getValue());
        assertEquals(url, idArgument.getValue());

    }
}