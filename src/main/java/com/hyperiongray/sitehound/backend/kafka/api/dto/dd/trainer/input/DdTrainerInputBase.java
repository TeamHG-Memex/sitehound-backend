package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input;

/**
 * Created by tomas on 29/09/16.
 */
public abstract class DdTrainerInputBase {

    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
