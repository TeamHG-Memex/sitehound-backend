package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 22/06/17.
 */
public class DdLoginInputDto extends KafkaDto{

    @JsonProperty("workspace_id")
    private String workspaceId;

    @JsonProperty("job_id")
    private String jobId;

    private String url;
    private List<String> keys = new LinkedList<>();
    private String screenshot;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    @Override
    public String toString() {
        String scr = screenshot==null ? "empty" : ""+screenshot.length();
        return "DdLoginInputDto{" +
                "workspaceId='" + workspaceId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", url='" + url + '\'' +
                ", keys=" + keys +
                ", screenshot='" + scr + '\'' +
                '}';
    }
}
