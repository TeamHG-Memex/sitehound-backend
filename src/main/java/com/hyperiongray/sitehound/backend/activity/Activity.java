package com.hyperiongray.sitehound.backend.activity;

/**
 * Created by tomas on 22/03/17.
 */
public interface Activity {

    void listen(
            String data,
//            Integer key,
            int partition,
            String topic);
}
