package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.event;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */

/**
 * these are the serialized args sent along with the event.
 */
public class DdCrawlerInputStartArgs extends KafkaDto{

    private String broadness;
    private Integer nResults;
    private String jobId;

    public String getBroadness() {
        return broadness;
    }

    public void setBroadness(String broadness) {
        this.broadness = broadness;
    }

    public Integer getnResults() {
        return nResults;
    }

    public void setnResults(Integer nResults) {
        this.nResults = nResults;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
