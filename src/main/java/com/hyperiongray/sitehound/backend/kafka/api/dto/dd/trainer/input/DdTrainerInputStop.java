package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input;

/**
 * Created by tomas on 29/09/16.
 */
public class DdTrainerInputStop{

    private String id;
    private static final Boolean stop = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStop() {
        return stop;
    }

//    public void setStop(Boolean stop) {
//        this.stop = stop;
//    }
}
