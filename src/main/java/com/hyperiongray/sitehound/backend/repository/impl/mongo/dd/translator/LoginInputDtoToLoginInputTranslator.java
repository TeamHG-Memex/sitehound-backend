package com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.translator;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tomas on 22/06/17.
 */
@Service
public class LoginInputDtoToLoginInputTranslator {

    public DdLoginInput translate(DdLoginInputDto ddLoginInputDto){
        Map<String, String> keyValues = ddLoginInputDto.getKeys().stream()
        .collect(Collectors.toMap(x->x,x-> ""));

        return new DdLoginInput.Builder()
                .withWorkspaceId(ddLoginInputDto.getWorkspaceId())
                .withJobId(ddLoginInputDto.getJobId())
                .withUrl(ddLoginInputDto.getUrl())
                .withDomain(ddLoginInputDto.getDomain())
                .withKeyValues(keyValues)
                .withKeysOrder(ddLoginInputDto.getKeys())
                .withScreenshot(ddLoginInputDto.getScreenshot())
                .build();
    }

}
