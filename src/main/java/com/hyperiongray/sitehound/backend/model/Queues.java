package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 28/09/16.
 */
public enum  Queues {

    AQUARIUM_INPUT(ZookeeperType.HG, "aquarium-input"),
    DD_MODELER_INPUT(ZookeeperType.HG, "dd-modeler-input"),
    DD_TRAINER_INPUT(ZookeeperType.HG, "dd-trainer-input"),
    DD_CRAWLER_INPUT(ZookeeperType.HG, "dd-crawler-input"),
    DD_CRAWLER_HINTS_INPUT(ZookeeperType.HG, "dd-crawler-hints-input")//,

 ;


    private final ZookeeperType zookeeperType;
    private final String subscriberTopic;


    Queues(ZookeeperType zookeeperType, String subscriberTopic){
        this.zookeeperType = zookeeperType;
        this.subscriberTopic = subscriberTopic;
    }

    public String getSubscriberTopic() {
        return subscriberTopic;
    }

    public String getSubscriberGroup() {
        return this.getSubscriberTopic() + "-group-prod";
    }

    public ZookeeperType getZookeeperType() {
        return zookeeperType;
    }
}

