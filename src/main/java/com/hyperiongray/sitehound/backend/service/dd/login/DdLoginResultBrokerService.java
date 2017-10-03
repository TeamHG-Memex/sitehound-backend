package com.hyperiongray.sitehound.backend.service.dd.login;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginResultDto;
import com.hyperiongray.sitehound.backend.model.DdLoginResult;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdLoginRepository;
import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 22/06/17.
 */
@Service
public class DdLoginResultBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdLoginResultBrokerService.class);

    @Autowired private DdLoginRepository ddLoginRepository;

    @Override
    public void process(String jsonInput){
        try{
            LOGGER.info("DdLoginResultBrokerService receiving: " + jsonInput);
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdLoginResultDto> jsonMapper= new JsonMapper();
            DdLoginResultDto ddLoginResultDto = jsonMapper.toObject(jsonInput, DdLoginResultDto.class);
            DdLoginResult ddLoginResult = translate(ddLoginResultDto);
            ddLoginRepository.updateResult(ddLoginResult);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }

    private DdLoginResult translate(DdLoginResultDto ddLoginResultDto) {
        DdLoginResult ddLoginResult = new DdLoginResult();
        ddLoginResult.setId(ddLoginResultDto.getId());
        ddLoginResult.setResult(ddLoginResultDto.getResult());
        return ddLoginResult;
    }

}
