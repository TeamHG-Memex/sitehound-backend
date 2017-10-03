package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.AbstractElasticsearchRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by tomas on 19/07/17.
 */
@Repository
@Deprecated
public class TrainerModelRepository //extends AbstractElasticsearchRepository<DdTrainerOutputModel>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerModelRepository.class);

    @Autowired
    private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

    private String indexName ="trainer";
    private String typeName = "model";

    public void save(String workspaceId, DdTrainerOutputModel ddTrainerOutputModel) throws IOException {
        LOGGER.info("About to save Trainer Model to ES:" + workspaceId);
        elasticsearchDatabaseClient.save(indexName, typeName, workspaceId, ddTrainerOutputModel);
        LOGGER.info("done saving ddTrainerOutputModel");
    }

    public DdTrainerOutputModel get(String workspaceId) throws IOException {
        return elasticsearchDatabaseClient.get(indexName, typeName, workspaceId, DdTrainerOutputModel.class);
    }

    public void delete(String workspaceId) throws IOException {
        elasticsearchDatabaseClient.delete(indexName, typeName, workspaceId);
    }

}
