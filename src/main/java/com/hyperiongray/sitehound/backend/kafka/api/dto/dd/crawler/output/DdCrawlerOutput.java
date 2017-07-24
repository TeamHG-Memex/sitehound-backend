package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.PageSample;

import java.util.List;

/**
 * Created by tomas on 3/10/16.
 */

public class DdCrawlerOutput  extends KafkaDto {
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
}
