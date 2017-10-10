package com.hyperiongray.sitehound.backend.test.elasticsearch;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchRepositoryTemplate;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultWrapperDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import com.hyperiongray.sitehound.backend.service.crawler.excavator.ExcavatorSearchService;
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

    @MockBean private ElasticsearchRepositoryTemplate elasticsearchRepositoryTemplateMock;
    @MockBean
    ElasticsearchDatabaseClient elasticsearchDatabaseClient;
    @MockBean ExcavatorSearchService excavatorSearchService;

    private static final String workspaceId = "ws123456";
    private static final String url = "http://www.example.com";

    @Test
    public void save() throws Exception {
//        String workspaceId = "ws1111";
        CrawlResultDto crawlResultDto = new CrawlResultDto(url);
        AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);

        instance.save(url, analyzedCrawlResultDto);

        ArgumentCaptor<String> indexNameArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> analyzedCrawlResultDtoArgument = ArgumentCaptor.forClass(String.class);
        verify(elasticsearchRepositoryTemplateMock).save(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture(), analyzedCrawlResultDtoArgument.capture());

        assertEquals(CRAWLED_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(CRAWLED_TYPE_NAME, typeArgument.getValue());
        assertEquals(url, idArgument.getValue());

        AnalyzedCrawlResultWrapperDto analyzedCrawlResultWrapperDto = new AnalyzedCrawlResultWrapperDto();
        analyzedCrawlResultWrapperDto.setAnalyzedCrawlResultDto(analyzedCrawlResultDto);
//        analyzedCrawlResultWrapperDto.setWorkspaces(Sets.<String>newHashSet(workspaceId));

        assertEquals(analyzedCrawlResultWrapperDto, analyzedCrawlResultDtoArgument.getValue());

    }

    @Test
    public void get() throws Exception {
        ArgumentCaptor<String> indexNameArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idArgument = ArgumentCaptor.forClass(String.class);

        instance.get(url);

        verify(elasticsearchRepositoryTemplateMock).get(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture());
        assertEquals(CRAWLED_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(CRAWLED_TYPE_NAME, typeArgument.getValue());
        assertEquals(url, idArgument.getValue());

    }
}