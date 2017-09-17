package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;


public class DdModelerOutput extends KafkaDto {

    private String workspaceId; //TODO check integration test. They are breaking cause expecting id
    private String quality;
    private String model;

    @JsonProperty("workspace_id")
    public String getWorkspaceId() {
        return workspaceId;
    }
    @JsonProperty("workspace_id")
    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DdModelerOutput that = (DdModelerOutput) o;

        if (workspaceId != null ? !workspaceId.equals(that.workspaceId) : that.workspaceId != null) return false;
        if (quality != null ? !quality.equals(that.quality) : that.quality != null) return false;
        return model != null ? model.equals(that.model) : that.model == null;
    }

    @Override
    public int hashCode() {
        int result = workspaceId != null ? workspaceId.hashCode() : 0;
        result = 31 * result + (quality != null ? quality.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DdModelerOutput{" +
                "workspaceId='" + workspaceId + '\'' +
                ", quality='" + quality + '\'' +
                ", model='" + (model!=null? model.length(): "") + '\'' +
                '}';
    }
}
