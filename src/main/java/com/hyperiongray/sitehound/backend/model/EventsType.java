package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 3/10/16.
 */

public enum EventsType {
//}
//public enum Events{
    DD_MODELER("dd-modeler"),
    DD_TRAINER("dd-trainer"),
    DD_CRAWLER("dd-crawler"),
    DD_DEEPCRAWLER("dd-deepcrawler"),
    BOOKMARK("bookmark"),
    JOB("job");

    private String code;
    EventsType (String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }

    public static EventsType getEnumFromString(String code) {
        for (EventsType event : EventsType.values()) {
            if (event.getCode().equals(code)) {
                return event;
            }
        }
        throw new IllegalArgumentException("the given code doesn't match any Event.");
    }
}