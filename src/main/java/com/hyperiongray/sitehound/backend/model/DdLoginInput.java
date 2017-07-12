package com.hyperiongray.sitehound.backend.model;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 22/06/17.
 */
public class DdLoginInput {

    private String id;
    private String workspaceId;
    private String jobId;
    private String url;
    private Map<String, String> keyValues;
    private String screenshot;
    private List<String> keysOrder;

    public String getId() {
        return id;
    }
    public String getWorkspaceId() {
        return workspaceId;
    }
    public String getJobId() {
        return jobId;
    }
    public String getUrl() {
        return url;
    }
    public String getScreenshot() {
        return screenshot;
    }
    public Map<String, String> getKeyValues() {
        return keyValues;
    }
    public List<String> getKeysOrder() {
        return keysOrder;
    }


    public static class Builder {
        private String id;
        private String workspaceId;
        private String jobId;
        private String url;
        private Map<String, String> keyValues = new HashMap<>();
        private String screenshot;
        private List<String> keysOrder = new LinkedList<>();

        public Builder withId(String id){
            this.id = id;
            return this;
        }
        public Builder withWorkspaceId(String workspaceId){
            this.workspaceId = workspaceId;
            return this;
        }
        public Builder withJobId(String jobId){
            this.jobId = jobId;
            return this;
        }
        public Builder withUrl(String url){
            this.url = url;
            return this;
        }
        public Builder withKeyValues(Map<String,String> keyValues){
            this.keyValues = keyValues;
            return this;
        }
        public Builder withScreenshot(String screenshot){
            this.screenshot = screenshot;
            return this;
        }
        public Builder withKeysOrder(List<String> keysOrder){
            this.keysOrder = keysOrder;
            return this;
        }

        public DdLoginInput build(){
            org.springframework.util.Assert.hasText(this.workspaceId);
            org.springframework.util.Assert.hasText(this.url);
            org.springframework.util.Assert.notEmpty(this.keysOrder);
            for(String key : keysOrder){
                org.springframework.util.Assert.notNull(this.keyValues.get(key));
            }
            org.springframework.util.Assert.isTrue(this.keysOrder.size() == this.keyValues.size());
            return new DdLoginInput(this);
        }
    }

    private DdLoginInput(Builder builder) {
        this.id = builder.id;
        this.workspaceId = builder.workspaceId;
        this.jobId = builder.jobId;
        this.url = builder.url;
        this.keyValues = builder.keyValues;
        this.screenshot = builder.screenshot;
        this.keysOrder = builder.keysOrder;
    }

    @Override
    public String toString() {
        String screenshotStr = null;
        if (screenshot!=null){
            screenshotStr = "" + screenshot.length();
        }
        return "DdLoginInput{" +
                "id='" + id + '\'' +
                ", workspaceId='" + workspaceId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", url='" + url + '\'' +
                ", keyValues=" + keyValues +
                ", screenshot='" + screenshotStr + '\'' +
                ", keysOrder=" + keysOrder +
                '}';
    }
}
