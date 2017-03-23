package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputBase;

/**
 * Created by tomas on 29/09/16.
 */
public class DdCrawlerInputStop extends DdTrainerInputBase {

    private static final Boolean stop = true;

    public Boolean getStop() {
        return stop;
    }

}
