package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by tomas on 2/10/16.
 */
public class DdDeepcrawlerOutputDto extends KafkaDto {

    /** crawl id */
    private String id;
    private String workspaceId;
    private List<PageSampleDto> pageSamples;

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
    public List<PageSampleDto> getPageSamples() {
        return pageSamples;
    }

    @JsonProperty("page_samples")
    public void setPageSamples(List<PageSampleDto> pageSamples) {
        this.pageSamples = pageSamples;
    }

}
