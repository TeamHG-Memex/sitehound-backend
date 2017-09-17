package com.hyperiongray.sitehound.backend.activity;

public enum  TopicsEnum {

    DD_LOGIN_INPUT("dd-login-input"),
    DD_LOGIN_RESULT("dd-login-result"),
    ;


    private String topicName;

    TopicsEnum(String topicName){
        this.topicName = topicName;
    }

    public String getTopicName(){
        return topicName;
    }

}
