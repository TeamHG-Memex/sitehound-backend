package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 12/07/17.
 */
public class DdModelerProgress  extends KafkaDto {

    private String id;

    private Double percentageDone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("percentage_done")
    public Double getPercentageDone() {
        return percentageDone;
    }

    public void setPercentageDone(Double percentageDone) {
        this.percentageDone = percentageDone;
    }


    @Override
    public String toString() {
        return "DdModelerProgress{" +
                "id='" + id + '\'' +
                ", percentageDone='" + percentageDone + '\'' +
                '}';
    }
}
