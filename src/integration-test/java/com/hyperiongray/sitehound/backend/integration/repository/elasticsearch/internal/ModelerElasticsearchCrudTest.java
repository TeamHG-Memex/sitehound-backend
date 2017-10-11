package com.hyperiongray.sitehound.backend.integration.repository.elasticsearch.internal;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ModelerModelDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository.MODELER_INDEX_NAME;
import static com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository.MODELER_TYPE_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("integration-test")
@TestPropertySource(locations = {"file:config/properties/elasticsearch.properties"})
public class ModelerElasticsearchCrudTest {

    @Autowired private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

    private static String workspaceId = "wsModelerClientTest00001".toLowerCase();
    private static String model = "....model....wsModelerClientTest00001...";

    private final JsonMapper<ModelerModelDto> modelerModelDtoJsonMapper = new JsonMapper<ModelerModelDto>();

    @Test
    public void crudModeler() throws Exception {


        elasticsearchDatabaseClient.delete(MODELER_INDEX_NAME, MODELER_TYPE_NAME, workspaceId);

        Optional<String> stringOptionalInitial = elasticsearchDatabaseClient.get(MODELER_INDEX_NAME, MODELER_TYPE_NAME, workspaceId);
        Assert.assertTrue(!stringOptionalInitial.isPresent());

        ModelerModelDto modelerModelDto = new ModelerModelDto();
        modelerModelDto.setModel(model);
        String modelerModelDtoAsString = modelerModelDtoJsonMapper.toJson(modelerModelDto);
        elasticsearchDatabaseClient.save(MODELER_INDEX_NAME, MODELER_TYPE_NAME, workspaceId, modelerModelDtoAsString);


        Optional<String> stringOptionalSaved = elasticsearchDatabaseClient.get(MODELER_INDEX_NAME, MODELER_TYPE_NAME, workspaceId);
        Assert.assertTrue(stringOptionalSaved.isPresent());
        ModelerModelDto modelerModelDtoSaved = modelerModelDtoJsonMapper.fromJSON(stringOptionalSaved.get(), ModelerModelDto.class);
        Assert.assertEquals(model, modelerModelDtoSaved.getModel());

        elasticsearchDatabaseClient.delete(MODELER_INDEX_NAME, MODELER_TYPE_NAME, workspaceId);

        Optional<String> stringOptionalDeleted = elasticsearchDatabaseClient.get(MODELER_INDEX_NAME, MODELER_TYPE_NAME, workspaceId);
        Assert.assertTrue(!stringOptionalDeleted.isPresent());
    }

}