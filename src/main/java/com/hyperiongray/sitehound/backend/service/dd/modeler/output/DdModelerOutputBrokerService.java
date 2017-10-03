package com.hyperiongray.sitehound.backend.service.dd.modeler.output;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdModelerProgressRepository;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 28/09/16.
 */
@Service
public class DdModelerOutputBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerOutputBrokerService.class);

    @Autowired private ElasticsearchModelerModelRepository elasticsearchModelerModelRepository;
    @Autowired private DdModelerProgressRepository ddModelerProgressRepository;

    @Override
    public void process(String jsonInput){
        try{
            LOGGER.debug("Receiving response from DdModelerOutputBrokerService size: " + jsonInput.length());
            JsonMapper<DdModelerOutput> jsonMapper= new JsonMapper();
            DdModelerOutput ddModelerOutput = jsonMapper.toObject(jsonInput, DdModelerOutput.class);
            LOGGER.info("Receiving response from DdModelerOutputBrokerService ddModelerOutput: " + ddModelerOutput);
            elasticsearchModelerModelRepository.save(ddModelerOutput);
            ddModelerProgressRepository.saveQuality(ddModelerOutput);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }


}
