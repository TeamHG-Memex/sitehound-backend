package com.hyperiongray.sitehound.backend.service.dd.login;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdLoginRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator.LoginInputDtoToLoginInputTranslator;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import com.hyperiongray.sitehound.backend.service.crawler.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 22/06/17.
 */
@Service
public class DdLoginInputBrokerService implements BrokerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdLoginInputBrokerService.class);

    @Autowired private LoginInputDtoToLoginInputTranslator loginInputDtoToLoginInputTranslator;
    @Autowired private DdLoginRepository ddLoginRepository;

    @Override
    public void process(String jsonInput){
        try{
            LOGGER.info("DdLoginInputBrokerService receiving: " + jsonInput);
            LOGGER.debug("Receiving response: " + jsonInput);
            JsonMapper<DdLoginInputDto> jsonMapper= new JsonMapper();
            DdLoginInputDto ddLoginInputDto = jsonMapper.toObject(jsonInput, DdLoginInputDto.class);
            DdLoginInput ddLoginInput = loginInputDtoToLoginInputTranslator.translate(ddLoginInputDto);
            ddLoginRepository.save(ddLoginInput);
        }
        catch(Exception e){
            LOGGER.error("ERROR:" + jsonInput, e);
        }
    }
}
