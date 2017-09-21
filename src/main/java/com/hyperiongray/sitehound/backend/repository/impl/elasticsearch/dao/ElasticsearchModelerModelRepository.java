package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.AbstractElasticsearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by tomas on 19/07/17.
 */
@Repository
public class ElasticsearchModelerModelRepository extends AbstractElasticsearchRepository<DdModelerOutput> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchModelerModelRepository.class);

    private String indexName ="modeler";
    private String typeName = "model";

    // receives the workspaceId
    public void save(DdModelerOutput ddModelerOutput) throws IOException {
        LOGGER.info("About to save Modeler model to ES:" + ddModelerOutput.getWorkspaceId());
        super.save(indexName, typeName, ddModelerOutput.getWorkspaceId(), ddModelerOutput);
        LOGGER.info("done saving DdModelerOutput");
    }

    public DdModelerOutput get(String id) throws IOException {
        return super.get(indexName, typeName, id, DdModelerOutput.class);
    }

    public void delete(String id) throws IOException {
        super.delete(indexName, typeName, id);
    }

}