package com.hyperiongray.sitehound.backend.service.aquarium.utils;

//import org.hyperiongray.googlecrawler.service.aquarium.callback.service.impl.BaseAquariumCallbackService;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.ScorerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tomas on 2/15/16.
 */
public class AquariumCallbackServiceMockUtils{

	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumCallbackServiceMockUtils.class);

//	public static void mockScorerService(BaseAquariumCallbackService aquariumCallbackService ){
//		ScorerService mockScorerService = new MockScorerService();
//		ReflectionTestUtils.setField(aquariumCallbackService, "scorerService", mockScorerService);
//	}

	public static class MockScorerService extends ScorerService{
//		public void indexTrainedData(){LOGGER.info("--- USING MOCK METHOD ---");}
			public double score(String workspace, String jobId, String url, String text){
			LOGGER.info("--- USING MOCK METHOD score()---");
			return 0.40;
		}
	}

}
