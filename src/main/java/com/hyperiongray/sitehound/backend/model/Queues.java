package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 28/09/16.
 */
public enum  Queues {


//    KEYWORDS_INPUT(ZookeeperType.HG, "google-keywords-input"),
//    BROADCRAWLER_INPUT(ZookeeperType.HG,"broadcrawler-input"),
//    IMPORT_URL(ZookeeperType.HG, "import-url-input"),
    AQUARIUM_INPUT(ZookeeperType.HG, "aquarium-input"),
//    EVENTS(ZookeeperType.HG, "events-input"),
    DD_MODELER_INPUT(ZookeeperType.HG, "dd-modeler-input"),
//    DD_MODELER_OUTPUT(ZookeeperType.HG, "dd-modeler-output"),
    DD_TRAINER_INPUT(ZookeeperType.HG, "dd-trainer-input"),
//    DD_TRAINER_OUTPUT_MODEL(ZookeeperType.HG, "dd-trainer-output-model"),
//    DD_TRAINER_OUTPUT_PAGES(ZookeeperType.HG, "dd-trainer-output-pages"),
//    DD_TRAINER_OUTPUT_PROGRESS(ZookeeperType.HG, "dd-trainer-output-progress"),
    DD_CRAWLER_INPUT(ZookeeperType.HG, "dd-crawler-input")//,
//    DD_CRAWLER_OUTPUT_PAGES(ZookeeperType.HG, "dd-crawler-output-pages"),
//    DD_CRAWLER_OUTPUT_PROGRESS(ZookeeperType.HG, "dd-crawler-output-progress")
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

