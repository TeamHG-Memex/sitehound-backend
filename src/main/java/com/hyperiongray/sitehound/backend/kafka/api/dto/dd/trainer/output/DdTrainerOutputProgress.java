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
    public String toString() {
        return "DdTrainerOutputProgress{" +
                "workspaceId='" + workspaceId + '\'' +
                ", progress='" + progress + '\'' +
                ", percentageDone=" + percentageDone +
                '}';
    }
}
