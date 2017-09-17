package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by tomas on 3/10/16.
 */

public class DdCrawlerOutputPages extends KafkaDto {

    private String id;
    private String workspaceId;
    private List<PageSample> pageSamples;


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

    @JsonProperty("page_samples")
    public List<PageSample> getPageSamples() {
        return pageSamples;
    }
    @JsonProperty("page_samples")
    public void setPageSamples(List<PageSample> pageSamples) {
        this.pageSamples = pageSamples;
    }
}
