package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 28/08/17.
 */
public class DeepcrawlerPageRequest {

    private String url;
    private String domain;
    private boolean isHome;

    public DeepcrawlerPageRequest(String url, String domain, boolean isHome) {
        this.url = url;
        this.domain = domain;
        this.isHome = isHome;
    }

    public String getUrl() {
        return url;
    }

    public String getDomain() {
        return domain;
    }

    public boolean getIsHome() {
        return isHome;
    }
}
