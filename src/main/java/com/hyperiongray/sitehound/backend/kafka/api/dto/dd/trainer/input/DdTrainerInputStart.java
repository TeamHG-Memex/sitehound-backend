package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
public class DdTrainerInputStart{

    private String id;
    private String workspaceId;
    private String page_model;
    private List<String> seeds;

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

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getPage_model() {
        return page_model;
    }

    public void setPage_model(String page_model) {
        this.page_model = page_model;
    }

    public List<String> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<String> seeds) {
        this.seeds = seeds;
    }

}
