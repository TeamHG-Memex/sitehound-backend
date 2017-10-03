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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgressDto that = (ProgressDto) o;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (pagesFetched != null ? !pagesFetched.equals(that.pagesFetched) : that.pagesFetched != null) return false;
        if (rpm != null ? !rpm.equals(that.rpm) : that.rpm != null) return false;
        return domains != null ? domains.equals(that.domains) : that.domains == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (pagesFetched != null ? pagesFetched.hashCode() : 0);
        result = 31 * result + (rpm != null ? rpm.hashCode() : 0);
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        return result;
    }
}
