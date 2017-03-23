package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output;

/**
 * Created by tomas on 2/10/16.
 */
public class DdCrawlerOutputProgress {

    private String id;

    private String progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
