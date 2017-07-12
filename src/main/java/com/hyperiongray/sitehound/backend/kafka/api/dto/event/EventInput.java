package com.hyperiongray.sitehound.backend.kafka.api.dto.event;

/**
 * Created by tomas on 28/09/16.
 */

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonIgnore;

public class EventInput extends KafkaDto{

//    private Metadata metadata;
    private String timestamp;
    private String workspaceId;
    private String event;
    private String action;

    @JsonIgnore private String arguments;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

//    public Metadata getMetadata() {
//        return metadata;
//    }
//
//    public void setMetadata(Metadata metadata) {
//        this.metadata = metadata;
//    }
//
    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
