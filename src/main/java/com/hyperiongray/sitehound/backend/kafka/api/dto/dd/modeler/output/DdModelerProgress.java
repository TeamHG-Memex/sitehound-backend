package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 12/07/17.
 */
public class DdModelerProgress  extends KafkaDto {

    private String id;

    @JsonProperty("percentage_done")
    private Double percentageDone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPercentageDone() {
        return percentageDone;
    }

    public void setPercentageDone(Double percentageDone) {
        this.percentageDone = percentageDone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DdModelerProgress that = (DdModelerProgress) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return percentageDone != null ? percentageDone.equals(that.percentageDone) : that.percentageDone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (percentageDone != null ? percentageDone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DdModelerProgress{" +
                "id='" + id + '\'' +
                ", percentageDone='" + percentageDone + '\'' +
                '}';
    }

}
