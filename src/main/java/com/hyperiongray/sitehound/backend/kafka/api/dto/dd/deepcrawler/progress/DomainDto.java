package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 27/08/17.
 */
public class DomainDto {

    private String url;
    private String domain;
    private String status; // valids are: running | finished | failed
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

    @JsonProperty("pages_fetched")
    public Integer getPagesFetched() {
        return pagesFetched;
    }

    @JsonProperty("pages_fetched")
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
        return "DomainDto{" +
                "url='" + url + '\'' +
                ", domain='" + domain + '\'' +
                ", status=" + status +
                ", pagesFetched=" + pagesFetched +
                ", rpm=" + rpm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainDto domainDto = (DomainDto) o;

        if (url != null ? !url.equals(domainDto.url) : domainDto.url != null) return false;
        if (domain != null ? !domain.equals(domainDto.domain) : domainDto.domain != null) return false;
        if (status != null ? !status.equals(domainDto.status) : domainDto.status != null) return false;
        if (pagesFetched != null ? !pagesFetched.equals(domainDto.pagesFetched) : domainDto.pagesFetched != null)
            return false;
        return rpm != null ? rpm.equals(domainDto.rpm) : domainDto.rpm == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (pagesFetched != null ? pagesFetched.hashCode() : 0);
        result = 31 * result + (rpm != null ? rpm.hashCode() : 0);
        return result;
    }
}