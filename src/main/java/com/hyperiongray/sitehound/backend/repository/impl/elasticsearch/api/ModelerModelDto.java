package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import java.io.Serializable;

public class ModelerModelDto implements Serializable {

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "ModelerModelDto{" +
                "model='" + model + '\'' +
                '}';
    }
}
