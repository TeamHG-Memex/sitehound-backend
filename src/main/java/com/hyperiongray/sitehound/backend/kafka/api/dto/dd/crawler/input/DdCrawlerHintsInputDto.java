package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 9/06/17.
 */
public class DdCrawlerHintsInputDto extends KafkaDto {

    private String workspaceId;
    private String url;
    private boolean pinned;

    @JsonProperty("workspace_id")
    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    @Override
    public String toString() {
        return "DdCrawlerHintsInputDto{" +
                "workspaceId='" + workspaceId + '\'' +
                ", url='" + url + '\'' +
                ", pinned=" + pinned +
                '}';
    }
}
