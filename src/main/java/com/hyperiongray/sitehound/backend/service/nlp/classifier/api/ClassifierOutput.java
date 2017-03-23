package com.hyperiongray.sitehound.backend.service.nlp.classifier.api;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tomas on 1/03/16.
 */
public class ClassifierOutput {

    private Set<String> categories = new HashSet<>();

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}
