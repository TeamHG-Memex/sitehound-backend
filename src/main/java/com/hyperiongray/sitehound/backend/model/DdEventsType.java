package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 3/10/16.
 */

public enum DdEventsType {
//}
//public enum Events{
    DD_MODELER("dd-modeler"),
    DD_TRAINER("dd-trainer"),
    DD_CRAWLER("dd-crawler");

    private String code;
    DdEventsType (String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }

    public static DdEventsType getEnumFromString(String code) {
        for (DdEventsType event : DdEventsType.values()) {
            if (event.getCode().equals(code)) {
                return event;
            }
        }
        throw new IllegalArgumentException("the given code doesn't match any Event.");
    }
}