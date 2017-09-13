package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 * Created by tomas on 2/10/16.
 */
public class DdDeepcrawlerProgressDto extends KafkaDto {

    private String id;

    private ProgressDto progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProgressDto getProgress() {
        return progress;
    }

    public void setProgress(ProgressDto progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "DdDeepcrawlerProgress{" +
                "id='" + id + '\'' +
                ", progress='" + progress + '\'' +
                '}';
    }

}
