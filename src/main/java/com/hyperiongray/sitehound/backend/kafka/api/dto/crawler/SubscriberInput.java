package com.hyperiongray.sitehound.backend.kafka.api.dto.crawler;

/**
 * Created by tomas on 5/20/15.
 */

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 # message = {
 #     'included': included,
 #     'excluded': excluded,
 #     'source': self.app_instance,
 #     'callback-queue': self.KEYWORDS_TOPIC_OUTPUT,
 #     'timestamp': time.time(),
 #     'hr-timestamp': strftime("%Y-%m-%d %H:%M:%S", gmtime()),
 #     'relevantUrl': ["http://www.filmaffinity.com/es/film620090.html", "http://www.imdb.com/title/tt1392190/"],
 #     'irrelevantUrl': ["http://stackoverflow.com/", "https://es.wikipedia.org/wiki/Mad_Max"]
 # }


 "http://es.thefreedictionary.com/prob%25C3%25A9", "http://michael.richter.name/blogs/why-i-no-longer-contribute-to-st
 ackoverflow", "http://www.sensacine.com/peliculas/pelicula-125054/", "https://en.wikipedia.org/wiki/Probe", "https:/
 /en.wiktionary.org/wiki/probe", "http://www.merriam-webster.com/dictionary/probe", "http://cdict.net/q/probe", "http
 ://diccionario.reverso.net/ingles-espanol/probe", "http://www.restaurantbarceloneta.com/inicio/", "https://es.
 .org/wiki/La_Barceloneta", "http://es.bab.la/diccionario/ingles-espanol/probe"], "workspace": "it", "nResults": 20,
 "timestamp": 1435692798.097804,

 */


public class SubscriberInput extends KafkaDto{

    private List<String> included;
    private List<String> excluded;
//    Map<String> words
//    private String source;
//    private String callbackQueue;
//    private Long timestamp;
    private String strTimestamp;
    private String workspace;
    private int nResults;
    private List<String> relevantUrl;
    private List<String> irrelevantUrl;
    private List<String> existentUrl;
    private String jobId;

    private String crawlProvider;
    private List<String> crawlSources;
    private Constants.KeywordSourceType keywordSourceType;



    public List<String> getIncluded() {
        if(included==null){
            included = new ArrayList<>();
        }
        return included;
    }

    public void setIncluded(List<String> included) {
        this.included = included;
    }

    public List<String> getExcluded() {
        if(excluded==null){
            excluded = new ArrayList<>();
        }
        return excluded;
    }

    public void setExcluded(List<String> excluded) {
        this.excluded = excluded;
    }
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getCallbackQueue() {
//        return callbackQueue;
//    }
//
//    public void setCallbackQueue(String callbackQueue) {
//        this.callbackQueue = callbackQueue;
//    }
//
//    public Long getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Long timestamp) {
//        this.timestamp = timestamp;
//    }

    public String getStrTimestamp() {
        return strTimestamp;
    }

    public void setStrTimestamp(String strTimestamp) {
        this.strTimestamp = strTimestamp;
    }

    public int getnResults() {
        return nResults;
    }

    public void setnResults(int nResults) {
        this.nResults = nResults;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public List<String> getRelevantUrl() {
        if(relevantUrl==null)
            relevantUrl = new ArrayList<>();
        return relevantUrl;
    }

    public void setRelevantUrl(List<String> relevantUrl) {
        this.relevantUrl = relevantUrl;
    }

    public List<String> getIrrelevantUrl() {
        if(irrelevantUrl==null){
            irrelevantUrl = new ArrayList<>();
        }
        return irrelevantUrl;
    }

    public void setIrrelevantUrl(List<String> irrelevantUrl) {
        this.irrelevantUrl = irrelevantUrl;
    }


    public List<String> getExistentUrl() {
        if(existentUrl==null)
            existentUrl = new ArrayList<>();
        return existentUrl;
    }

    public void setExistentUrl(List<String> existentUrl) {
        this.existentUrl = existentUrl;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId=jobId;
    }

    public String getCrawlProvider(){
        return crawlProvider;
    }

    public void setCrawlProvider(String crawlProvider){
        this.crawlProvider=crawlProvider;
    }

    public List<String> getCrawlSources(){
        return crawlSources;
    }

    public void setCrawlSources(List<String> crawlSources){
        this.crawlSources=crawlSources;
    }


    public Constants.KeywordSourceType getKeywordSourceType() {
        return keywordSourceType;
    }

    public void setKeywordSourceType(Constants.KeywordSourceType keywordSourceType) {
        this.keywordSourceType = keywordSourceType;
    }

    @Override
    public String toString(){
        return "SubscriberInput{" +
                       "included=" + included +
                       ", excluded=" + excluded +
//                       ", source='" + source + '\'' +
//                       ", callbackQueue='" + callbackQueue + '\'' +
//                       ", timestamp=" + timestamp +
                       ", strTimestamp='" + strTimestamp + '\'' +
                       ", workspace='" + workspace + '\'' +
                       ", nResults=" + nResults +
                       ", relevantUrl=" + relevantUrl +
                       ", irrelevantUrl=" + irrelevantUrl +
                       ", existentUrl=" + existentUrl +
                       ", jobId='" + jobId + '\'' +
                       ", crawlProvider='" + crawlProvider + '\'' +
                       ", crawlSources=" + crawlSources +
                       ", keywordSourceType=" + keywordSourceType+
                       '}';
    }
}
