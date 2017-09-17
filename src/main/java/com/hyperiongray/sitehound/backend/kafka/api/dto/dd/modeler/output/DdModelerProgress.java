package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 12/07/17.
 */
public class DdModelerProgress  extends KafkaDto {

    private String workspaceId; //TODO check integration test. They are breaking cause expecting id
    private Double percentageDone;

    @JsonProperty("workspace_id")
    public String getWorkspaceId() {
        return workspaceId;
    }
    @JsonProperty("workspace_id")
    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    @JsonProperty("percentage_done")
    public Double getPercentageDone() {
        return percentageDone;
    }
    @JsonProperty("percentage_done")
    public void setPercentageDone(Double percentageDone) {
        this.percentageDone = percentageDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DdModelerProgress that = (DdModelerProgress) o;

        if (workspaceId != null ? !workspaceId.equals(that.workspaceId) : that.workspaceId != null) return false;
        return percentageDone != null ? percentageDone.equals(that.percentageDone) : that.percentageDone == null;
    }

    @Override
    public int hashCode() {
        int result = workspaceId != null ? workspaceId.hashCode() : 0;
        result = 31 * result + (percentageDone != null ? percentageDone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DdModelerProgress{" +
                "workspaceId='" + workspaceId + '\'' +
                ", percentageDone='" + percentageDone + '\'' +
                '}';
    }

}
