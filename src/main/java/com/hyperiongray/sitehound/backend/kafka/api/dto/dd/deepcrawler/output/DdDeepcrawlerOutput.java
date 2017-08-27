package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by tomas on 2/10/16.
 */
public class DdDeepcrawlerOutput extends KafkaDto {

    /** crawl id */
    private String id;

    @JsonProperty("page_sample")
    private List<PageSample> pageSamples;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PageSample> getPageSamples() {
        return pageSamples;
    }

    public void setPageSamples(List<PageSample> pageSamples) {
        this.pageSamples = pageSamples;
    }

}
