package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 2/10/16.
 */
public class DdCrawlerOutputProgress  extends KafkaDto {

    private String id;

    private String workspaceId;

    private String progress;

    private Double percentageDone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @JsonProperty("percentage_done")
    public Double getPercentageDone() {
        return percentageDone;
    }
    @JsonProperty("percentage_done")
    public void setPercentageDone(Double percentageDone) {
        this.percentageDone = percentageDone;
    }


    @Override
    public String toString() {
        return "DdCrawlerOutputProgress{" +
                "id='" + id + '\'' +
                ", progress='" + progress + '\'' +
                ", percentageDone=" + percentageDone +
                '}';
    }
}
