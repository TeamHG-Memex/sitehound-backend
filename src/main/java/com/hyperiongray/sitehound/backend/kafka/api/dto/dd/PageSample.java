package com.hyperiongray.sitehound.backend.kafka.api.dto.dd;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 * Created by tomas on 4/10/16.
 */
public class PageSample extends KafkaDto{

    private String url;
    private String domain;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
