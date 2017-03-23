package com.hyperiongray.sitehound.backend.kafka.api.dto.dd;

/**
 * Created by tomas on 4/10/16.
 */
public class PageSample {

    private String url;
    private Double score;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
