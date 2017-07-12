package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output;

/**
 * Created by tomas on 28/09/16.
 */

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 dd-modeler-output
 {
 "id": "the same id",
 "quality": "Accuracy is 0.84 and some other metric is 0.89",
 "model": "b64-encoded page classifier model"
 }


 */
public class DdModelerOutput extends KafkaDto {

    private String id;
    private String quality;
    private String model;

    public DdModelerOutput() {
    }

    public DdModelerOutput(String id, String quality, String model) {
        this.id = id;
        this.quality = quality;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
