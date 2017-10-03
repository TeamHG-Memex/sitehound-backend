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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelerModelDto that = (ModelerModelDto) o;

        return model != null ? model.equals(that.model) : that.model == null;
    }

    @Override
    public int hashCode() {
        return model != null ? model.hashCode() : 0;
    }
}
