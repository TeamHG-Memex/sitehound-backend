package com.hyperiongray.sitehound.backend.kafka.api.dto.event;

/**
 * Created by tomas on 28/09/16.
 */

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;

/**
 {"data":
        {"action": "getDdTrainerInputStart",
        "to": "hh-joogle",
        "from": "thh"},
 "event": "dd-training",
 "metadata":
        {"source": "888c62c8-8589-11e6-aba0-f4f26dc90928",
         "strTimestamp": "2016-09-28 14:40:55",
        "callbackQueue": "callback_queue_not_used",
        "workspace": "57e9742c166f1c072dcd33d4",
        "timestamp": 1475073655.649645}
 }


 */
public class EventInput extends KafkaDto{


//    private Data data;
    private String event;
    private String action;
    private Metadata metadata;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

//    public static class Data{
//
//        private String action;
//        private String to;
//        private String from;
//
//        public Data() {
//        }
//
//        public Data(String action, String to, String from) {
//            this.action = action;
//            this.to = to;
//            this.from = from;
//        }
//
//        public String getAction() {
//            return action;
//        }
//
//        public void setAction(String action) {
//            this.action = action;
//        }
//
//        public String getTo() {
//            return to;
//        }
//
//        public void setTo(String to) {
//            this.to = to;
//        }
//
//        public String getFrom() {
//            return from;
//        }
//
//        public void setFrom(String from) {
//            this.from = from;
//        }
//    }
}
