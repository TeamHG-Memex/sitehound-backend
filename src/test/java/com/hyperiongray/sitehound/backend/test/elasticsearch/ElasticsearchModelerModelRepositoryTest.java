package com.hyperiongray.sitehound.backend.test.elasticsearch;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchRepositoryTemplate;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ModelerModelDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository;
import com.hyperiongray.sitehound.backend.service.crawler.excavator.ExcavatorSearchService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Optional;

import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository.MODELER_INDEX_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository.MODELER_TYPE_NAME;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElasticsearchTestConfiguration.class})
@SpringBootTest
public class ElasticsearchModelerModelRepositoryTest {

    @Autowired private ElasticsearchModelerModelRepository instance;

    @MockBean
    private ElasticsearchRepositoryTemplate elasticsearchRepositoryTemplateMock;

    @MockBean
    ElasticsearchDatabaseClient elasticsearchDatabaseClient;
    @MockBean
    ExcavatorSearchService excavatorSearchService;

    private static final String workspaceId = "ws123456";

    @Ignore
    @Test
    public void save() throws Exception {
        DdModelerOutput ddModelerOutput = new DdModelerOutput();
        ddModelerOutput.setQuality("...quality...");
        String model = ".....model...";
        ddModelerOutput.setModel(model);
        ddModelerOutput.setWorkspaceId(workspaceId);
        instance.save(ddModelerOutput);

        ArgumentCaptor<String> indexNameArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> modelerModelDtoArgument = ArgumentCaptor.forClass(String.class);
        verify(elasticsearchRepositoryTemplateMock).save(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture(), modelerModelDtoArgument.capture());

        assertEquals(MODELER_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(MODELER_TYPE_NAME, typeArgument.getValue());
        assertEquals(workspaceId, idArgument.getValue());

        ModelerModelDto modelerModelDto = new ModelerModelDto();
        modelerModelDto.setModel(model);
        assertEquals(modelerModelDto, modelerModelDtoArgument.getValue());
    }

    @Test
    @Ignore
    public void get(){

        try {
            when(elasticsearchDatabaseClient.get(MODELER_INDEX_NAME, MODELER_TYPE_NAME, workspaceId)).thenReturn(Optional.empty());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArgumentCaptor<String> indexNameArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idArgument = ArgumentCaptor.forClass(String.class);
        try {
            ModelerModelDto modelerModelDto = instance.get(workspaceId).get();
            verify(elasticsearchRepositoryTemplateMock).get(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(MODELER_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(MODELER_TYPE_NAME, typeArgument.getValue());
        assertEquals(workspaceId, idArgument.getValue());
    }

}