package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.AbstractElasticsearchRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ModelerModelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by tomas on 19/07/17.
 */
@Repository
public class ElasticsearchModelerModelRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchModelerModelRepository.class);

    @Autowired
    private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

    public final static String MODELER_INDEX_NAME ="modeler";
    public final static String MODELER_TYPE_NAME = "model";

    public void save(DdModelerOutput ddModelerOutput) throws IOException {
        LOGGER.info("About to save Modeler model to ES: " + ddModelerOutput.getWorkspaceId());

        ModelerModelDto modelerModelDto = new ModelerModelDto();
        modelerModelDto.setModel(ddModelerOutput.getModel());
        elasticsearchDatabaseClient.save(MODELER_INDEX_NAME, MODELER_TYPE_NAME, ddModelerOutput.getWorkspaceId(), modelerModelDto);
        LOGGER.info("done saving DdModelerOutput");
    }

    public ModelerModelDto get(String id) throws IOException {
        return elasticsearchDatabaseClient.get(MODELER_INDEX_NAME, MODELER_TYPE_NAME, id, ModelerModelDto.class);
    }

    public void delete(String id) throws IOException {
        elasticsearchDatabaseClient.delete(MODELER_INDEX_NAME, MODELER_TYPE_NAME, id);
    }

}
