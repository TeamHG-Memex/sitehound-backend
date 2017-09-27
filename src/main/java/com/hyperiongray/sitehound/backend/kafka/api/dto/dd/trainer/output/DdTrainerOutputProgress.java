package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 2/10/16.
 */
public class DdTrainerOutputProgress extends KafkaDto {

    private String workspaceId;
    private String progress;

    @JsonProperty("percentage_done")
    private Double percentageDone;

    @JsonProperty("workspace_id")
    public String getWorkspaceId() {
        return workspaceId;
    }
    @JsonProperty("workspace_id")
    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getProgress() {
        return progress;
    }
    public void setProgress(String progress) {
        this.progress = progress;
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

        DdTrainerOutputProgress that = (DdTrainerOutputProgress) o;

        if (workspaceId != null ? !workspaceId.equals(that.workspaceId) : that.workspaceId != null) return false;
        if (progress != null ? !progress.equals(that.progress) : that.progress != null) return false;
        return percentageDone != null ? percentageDone.equals(that.percentageDone) : that.percentageDone == null;
    }

    @Override
    public int hashCode() {
        int result = workspaceId != null ? workspaceId.hashCode() : 0;
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + (percentageDone != null ? percentageDone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DdTrainerOutputProgress{" +
                "workspaceId='" + workspaceId + '\'' +
                ", progress='" + progress + '\'' +
                ", percentageDone=" + percentageDone +
                '}';
    }
}
