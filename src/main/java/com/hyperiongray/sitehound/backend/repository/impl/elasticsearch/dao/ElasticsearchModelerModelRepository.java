package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.AbstractElasticsearchRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ModelerModelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by tomas on 19/07/17.
 */
@Repository
public class ElasticsearchModelerModelRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchModelerModelRepository.class);
    public final static String MODELER_INDEX_NAME ="modeler";
    public final static String MODELER_TYPE_NAME = "model";

    @Autowired
    private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

    private JsonMapper<ModelerModelDto> jsonMapper = new JsonMapper();

    private final JsonMapper<ModelerModelDto> modelerModelDtoJsonMapper = new JsonMapper<ModelerModelDto>();

    public void save(DdModelerOutput ddModelerOutput) throws IOException {
        LOGGER.info("About to save Modeler model to ES: " + ddModelerOutput.getWorkspaceId());

        ModelerModelDto modelerModelDto = new ModelerModelDto();
        modelerModelDto.setModel(ddModelerOutput.getModel());
        String modelerModelDtoAsString = modelerModelDtoJsonMapper.toString(modelerModelDto);
        elasticsearchDatabaseClient.save(MODELER_INDEX_NAME, MODELER_TYPE_NAME, ddModelerOutput.getWorkspaceId(), modelerModelDtoAsString);
        LOGGER.info("done saving DdModelerOutput");
    }

    public Optional<ModelerModelDto> get(String id) throws IOException {
        Optional<String> repositoryResult = elasticsearchDatabaseClient.get(MODELER_INDEX_NAME, MODELER_TYPE_NAME, id);

        Optional<ModelerModelDto> result = Optional.empty();
        if(repositoryResult.isPresent()){
            ModelerModelDto modelerModelDto = jsonMapper.toObject(repositoryResult.get(), ModelerModelDto.class);
            result = Optional.of(modelerModelDto);
        }

        return result;
    }

    public void delete(String id) throws IOException {
        elasticsearchDatabaseClient.delete(MODELER_INDEX_NAME, MODELER_TYPE_NAME, id);
    }

}
