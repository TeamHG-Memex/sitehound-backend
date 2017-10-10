package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
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
@Deprecated
public class TrainerModelRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerModelRepository.class);
/*
    @Autowired
    private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

    private JsonMapper<DdTrainerOutputModel> jsonMapper = new JsonMapper();

    private String indexName ="trainer";
    private String typeName = "model";

    public void save(String workspaceId, DdTrainerOutputModel ddTrainerOutputModel) throws IOException {
        LOGGER.info("About to save Trainer Model to ES:" + workspaceId);
        elasticsearchDatabaseClient.save(indexName, typeName, workspaceId, jsonMapper.toJson(ddTrainerOutputModel));
        LOGGER.info("done saving ddTrainerOutputModel");
    }

    public Optional<DdTrainerOutputModel> get(String workspaceId) throws IOException {
        Optional<String> repositoryResult = elasticsearchDatabaseClient.get(indexName, typeName, workspaceId);
        Optional<DdTrainerOutputModel> result = Optional.empty();
        if(repositoryResult.isPresent()){
            DdTrainerOutputModel modelerModelDto = jsonMapper.toObject(repositoryResult.get(), DdTrainerOutputModel.class);
            result = Optional.of(modelerModelDto);
        }
        return result;
    }

    public void delete(String workspaceId) throws IOException {
        elasticsearchDatabaseClient.delete(indexName, typeName, workspaceId);
    }
*/
}
