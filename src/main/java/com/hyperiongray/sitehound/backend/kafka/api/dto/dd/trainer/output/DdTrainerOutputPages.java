package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;

import java.util.List;

/**
 * Created by tomas on 30/09/16.
 */

/*
    { "id": "57e9742c166f1c072dcd33d4", "page_sample": [{"url":"http://example1.com", "score":30}, {"url":"http://example2.com","score:89"}]}
 */
public class DdTrainerOutputPages extends KafkaDto {
    private String id;
    private List<PageSample> page_sample;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<PageSample> getPage_sample() {
        return page_sample;
    }

    public void setPage_sample(List<PageSample> page_sample) {
        this.page_sample = page_sample;
    }

    @Override
    public String toString() {
        return "DdTrainerOutputPages{" +
                "id='" + id + '\'' +
                ", page_sample=" + page_sample +
                '}';
    }
}
