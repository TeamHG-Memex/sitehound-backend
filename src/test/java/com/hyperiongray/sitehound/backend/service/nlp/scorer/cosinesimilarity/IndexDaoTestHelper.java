package com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity;

import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;

/**
 * Created by tomas on 11/29/15.
 */
public class IndexDaoTestHelper{

	public static TrainedCrawledUrl getIrrelevantGoogle(){
		TrainedCrawledUrl trainedCrawledUrl = new TrainedCrawledUrl();
		trainedCrawledUrl.setUrl("www.google.com");
		trainedCrawledUrl.setContent("Facebook te ayuda a comunicarte y compartir con las personas que forman parte de tu vida.");
		trainedCrawledUrl.setRelevant(false);
		return  trainedCrawledUrl;
	}

	public static TrainedCrawledUrl getRelevantVolleyMeetup_A(){
		TrainedCrawledUrl trainedCrawledUrl = new TrainedCrawledUrl();
		trainedCrawledUrl.setUrl("http://www.meetup.com/es/Barcelona-Beach-Volleyball-2x2-intermediate-and-advanced/");
		trainedCrawledUrl.setContent("Este grupo es para amantes de volei del playa que ya tengan un buen nivel para jugar 2x2 (solo intermedios y avanzados, no principiantes).");
		trainedCrawledUrl.setRelevant(true);
		return  trainedCrawledUrl;
	}

	public static TrainedCrawledUrl getRelevantVolleyMeetup_B(){
		TrainedCrawledUrl trainedCrawledUrl = new TrainedCrawledUrl();
		trainedCrawledUrl.setUrl("http://www.meetup.com/es/Barcelona-beach-volley-2x2-intermediate/");
		trainedCrawledUrl.setContent("Cualquiera que quiera jugar a beach volley para pasarlo bien! ! y que juegue un poco \uD83D\uDE0E. Lo siento pero no es para novatos \uD83D\uDE0C");
		trainedCrawledUrl.setRelevant(true);
		return  trainedCrawledUrl;
	}

}
