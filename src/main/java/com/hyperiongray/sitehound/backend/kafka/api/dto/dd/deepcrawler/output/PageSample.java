package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output;

/**
 * Created by tomas on 27/08/17.
 */
public class PageSample {
    private String url;
    private String domain;

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

    @Override
    public String toString() {
        return "PageSample{" +
                "url='" + url + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
