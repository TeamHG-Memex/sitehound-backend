package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 27/08/17.
 */
public class ProgressDto {


    private String status;
    private Integer pagesFetched;
    private Integer rpm;
    private List<DomainDto> domains = new ArrayList();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("pages_fetched")
    public Integer getPagesFetched() {
        return pagesFetched;
    }

    @JsonProperty("pages_fetched")
    public void setPagesFetched(Integer pagesFetched) {
        this.pagesFetched = pagesFetched;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public List<DomainDto> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainDto> domains) {
        this.domains = domains;
    }

    public void addDomains(DomainDto domain) {
        this.domains.add(domain);
    }

    @Override
    public String toString() {
        return "ProgressDto{" +
                "status='" + status + '\'' +
                ", pagesFetched=" + pagesFetched +
                ", rpm=" + rpm +
                ", domains=" + domains +
                '}';
    }
}
