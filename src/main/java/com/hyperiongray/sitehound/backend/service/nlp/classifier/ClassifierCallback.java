package com.hyperiongray.sitehound.backend.service.nlp.classifier;

/**
 * Created by tomas on 1/03/16.
 */
public class ClassifierCallback {//implements FutureCallback<Content> {
//
//    private JsonMapper<Set<String>> classifierJsonMapper = new JsonMapper();
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifierCallback.class);
//
//    public void completed(final Content content) {
//
//        try{
//            LOGGER.info("Aquarium response for: " + aquariumInput.getUrl());
//            AquariumInternal aquariumInternal = aquariumInternalJsonMapper.toObject(content.asString(Charset.forName("UTF-8")), AquariumInternal.class);
//            aquariumCallbackService.getDdTrainerInputStart(aquariumInput, aquariumInternal);
//        }
//        catch(Exception e){
//            LOGGER.error("Error In Callback", e);
//        }
//        finally{
//            LOGGER.info("Aquarium finished and inserted: " + aquariumInput.getUrl());
//            semaphore.release();
//            LOGGER.info("semaphores available permits: " + semaphore.availablePermits());
//        }
//    }
//
//    public void failed(final Exception ex) {
//        LOGGER.error("request failed: " + aquariumInput.getUrl(), ex);
//        semaphore.release();
//    }
//
//
//    public void cancelled() {
//        LOGGER.error("request cancelled: " + aquariumInput.getUrl());
//        semaphore.release();
//    }
}
