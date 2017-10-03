package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ModelerModelDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository.MODELER_INDEX_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository.MODELER_TYPE_NAME;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElasticsearchTestConfiguration.class})
@SpringBootTest
public class ElasticsearchModelerModelRepositoryTest {

    @Autowired private ElasticsearchModelerModelRepository instance;

    @MockBean
    private com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchRepositoryTemplate elasticsearchRepositoryTemplateMock;

    private static final String workspaceId = "ws123456";

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
        ArgumentCaptor<ModelerModelDto> modelerModelDtoArgument = ArgumentCaptor.forClass(ModelerModelDto.class);
        verify(elasticsearchRepositoryTemplateMock).save(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture(), modelerModelDtoArgument.capture());

        assertEquals(MODELER_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(MODELER_TYPE_NAME, typeArgument.getValue());
        assertEquals(workspaceId, idArgument.getValue());

        ModelerModelDto modelerModelDto = new ModelerModelDto();
        modelerModelDto.setModel(model);
        assertEquals(modelerModelDto, modelerModelDtoArgument.getValue());
    }

    @Test
    public void get() throws Exception {

        ModelerModelDto modelerModelDto = instance.get(workspaceId);
        ArgumentCaptor<String> indexNameArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idArgument = ArgumentCaptor.forClass(String.class);
        verify(elasticsearchRepositoryTemplateMock).get(any(), indexNameArgument.capture(), typeArgument.capture(), idArgument.capture(), any());

        assertEquals(MODELER_INDEX_NAME, indexNameArgument.getValue());
        assertEquals(MODELER_TYPE_NAME, typeArgument.getValue());
        assertEquals(workspaceId, idArgument.getValue());
    }

}

