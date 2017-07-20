package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 * Created by tomas on 29/09/16.
 */
public class DdCrawlerInputStopDto extends KafkaDto {


    private String id;
    private Boolean stop = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Boolean getStop() {
        return stop;
    }


    @Override
    public String toString() {
        return "DdCrawlerInputStopDto{" +
                "id='" + id + '\'' +
                ", stop=" + stop +
                '}';
    }
}
