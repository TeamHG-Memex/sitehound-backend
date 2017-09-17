package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by tomas on 30/09/16.
 */


public class DdTrainerOutputPages extends KafkaDto {


    private String workspaceId;

    private List<PageSample> pageSamples;


    @JsonProperty("workspace_id")
    public String getWorkspaceId() {
        return workspaceId;
    }
    @JsonProperty("workspace_id")
    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }


    @JsonProperty("page_samples")
    public List<PageSample> getPageSamples() {
        return pageSamples;
    }
    @JsonProperty("page_samples")
    public void setPageSamples(List<PageSample> pageSamples) {
        this.pageSamples = pageSamples;
    }

    @Override
    public String toString() {
        return "DdTrainerOutputPages{" +
                "workspaceId='" + workspaceId + '\'' +
                ", pageSamples=" + pageSamples +
                '}';
    }
}
