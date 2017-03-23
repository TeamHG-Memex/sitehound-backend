package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input;

/**
 * Created by tomas on 29/09/16.
 */
public class DdTrainerInputStop extends DdTrainerInputBase{

    private static final Boolean stop = true;

    public Boolean getStop() {
        return stop;
    }

//    public void setStop(Boolean stop) {
//        this.stop = stop;
//    }
}
