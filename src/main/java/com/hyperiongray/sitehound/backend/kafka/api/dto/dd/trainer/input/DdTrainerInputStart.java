package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input;

import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
public class DdTrainerInputStart extends DdTrainerInputBase{

    private String page_model;
    private List<String> seeds;

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
