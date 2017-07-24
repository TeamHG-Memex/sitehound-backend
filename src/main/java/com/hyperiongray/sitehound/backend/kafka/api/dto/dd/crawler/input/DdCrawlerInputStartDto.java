package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
public class DdCrawlerInputStartDto extends KafkaDto {

    private String id; // the jobId
    private String workspaceId;
    private String linkModel;
    private String pageModel;
    private List<String> seeds = new LinkedList<>();
    private List<String> hints = new LinkedList<>();
    private String broadness;
    private Integer pageLimit;



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

    @JsonProperty("link_model")
    public String getLinkModel() {
        return linkModel;
    }

    public void setLinkModel(String linkModel) {
        this.linkModel = linkModel;
    }

    @JsonProperty("page_model")
    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public List<String> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<String> seeds) {
        this.seeds = seeds;
    }

    @JsonProperty("page_limit")
    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getBroadness() {
        return broadness;
    }

    public void setBroadness(String broadness) {
        this.broadness = broadness;
    }

    public List<String> getHints() {
        return hints;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    @Override
    public String toString() {
        return "DdCrawlerInputStartDto{" +
                "id='" + id + '\'' +
                ", workspaceId='" + workspaceId + '\'' +
                ", linkModel length='" + linkModel.length() + '\'' +
                ", pageModel length='" + pageModel.length() + '\'' +
                ", seeds length=" + (seeds!=null? seeds.size():0) +
                ", hints length=" + (hints!=null? hints.size():0) +
                ", broadness='" + broadness + '\'' +
                ", pageLimit=" + pageLimit +
                '}';
    }
}
