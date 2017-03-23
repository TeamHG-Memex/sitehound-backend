package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input;

import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
public class DdCrawlerInputStart{

    private String id;
    private String link_model;
    private String page_model;
    private List<String> seeds;
    private Integer max_results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink_model() {
        return link_model;
    }

    public void setLink_model(String link_model) {
        this.link_model = link_model;
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

    public Integer getMax_results() {
        return max_results;
    }

    public void setMax_results(Integer max_results) {
        this.max_results = max_results;
    }
}
