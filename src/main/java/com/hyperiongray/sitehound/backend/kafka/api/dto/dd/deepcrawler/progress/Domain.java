package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 27/08/17.
 */
public class Domain {

    private String url;
    private String domain;
    private String status; // valids are: running | finished | failed
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", status=" + status +
                ", pagesFetched=" + pagesFetched +
                ", rpm=" + rpm +
                '}';
    }
}