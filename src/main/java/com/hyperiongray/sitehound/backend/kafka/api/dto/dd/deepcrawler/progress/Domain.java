package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 27/08/17.
 */
public class Domain {

    private String url;
    private String domain;
    private Boolean finished;
    @JsonProperty("pages_fetched")
    private Integer pagesFetched;
    private Integer rpm;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Integer getPagesFetched() {
        return pagesFetched;
    }

    public void setPagesFetched(Integer pagesFetched) {
        this.pagesFetched = pagesFetched;
    }

    public Integer getRpm() {
        return this.rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }


    @Override
    public String toString() {
        return "Domain{" +
                "url='" + url + '\'' +
                ", domain='" + domain + '\'' +
                ", finished=" + finished +
                ", pagesFetched=" + pagesFetched +
                ", rpm=" + rpm +
                '}';
    }
}