package com.hyperiongray.sitehound.backend.service.nlp.classifier.api;

/**
 * Created by tomas on 2/03/16.
 */
public class ClassifierInput {
    private String html;


    public ClassifierInput(String html) {
        this.html = html;
    }

    public String getHtml() {
        return html;
    }
}
