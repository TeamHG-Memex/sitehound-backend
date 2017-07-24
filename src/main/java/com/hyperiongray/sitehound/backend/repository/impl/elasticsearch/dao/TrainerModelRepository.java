package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output.DdTrainerOutputModel;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.AbstractElasticsearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by tomas on 19/07/17.
 */
@Repository
public class TrainerModelRepository extends AbstractElasticsearchRepository<DdTrainerOutputModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerModelRepository.class);

    private String indexName ="trainer";
    private String typeName = "model";

    public void save(String workspaceId, DdTrainerOutputModel ddTrainerOutputModel) throws IOException {
        LOGGER.info("About to save Trainer Model to ES:" + workspaceId);
        super.save(indexName, typeName, workspaceId, ddTrainerOutputModel);
        LOGGER.info("done saving ddTrainerOutputModel");

    }

    public DdTrainerOutputModel get(String workspaceId) throws IOException {
        return super.get(indexName, typeName, workspaceId, DdTrainerOutputModel.class);
    }

    public void delete(String workspaceId) throws IOException {
        super.delete(indexName, typeName, workspaceId);
    }

}
