package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 * Created by tomas on 30/09/16.
 */
public class DdTrainerOutputModel extends KafkaDto {

    private String id;
    private String link_model;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink_model() {
        return link_model;
    }

    public void setLink_model(String link_model) {
        this.link_model = link_model;
    }

    @Override
    public String toString() {
        return "DdTrainerOutputModel{" +
                "id='" + id + '\'' +
                ", link_model='" + (link_model!=null? link_model.length():"") + '\'' +
                '}';
    }
}
