package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 22/06/17.
 */
public class DdLoginResultDto extends KafkaDto{

    private String id;
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DdLoginResultDto{" +
                "id='" + id + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
