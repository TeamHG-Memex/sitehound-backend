package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
public class DdTrainerInputStart  extends KafkaDto {

    private String id;
    private String workspaceId;
    private String page_model;
    private List<String> seeds = new ArrayList<>();

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

    @JsonProperty("page_model")
    public String getPageModel() {
        return page_model;
    }

    @JsonProperty("page_model")
    public void setPageModel(String page_model) {
        this.page_model = page_model;
    }

    public List<String> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<String> seeds) {
        this.seeds = seeds;
    }

    @Override
    public String toString() {
        return "DdTrainerInputStart{" +
                "id='" + id + '\'' +
                ", workspaceId='" + workspaceId + '\'' +
                ", page_model='" + (page_model!=null?page_model.length() : "") + '\'' +
                ", seeds=" + (seeds!=null? seeds.size(): 0) +
                '}';
    }
}
