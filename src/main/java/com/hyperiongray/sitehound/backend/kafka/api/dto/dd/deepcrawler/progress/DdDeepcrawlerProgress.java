package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 2/10/16.
 */
public class DdDeepcrawlerProgress extends KafkaDto {

    private String id;

    private Progress progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
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
