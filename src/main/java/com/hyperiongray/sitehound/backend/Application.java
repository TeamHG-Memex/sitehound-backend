package com.hyperiongray.sitehound.backend;

import com.hyperiongray.sitehound.backend.activity.*;
import com.hyperiongray.sitehound.backend.activity.dd.crawler.DdCrawlerOutputPagesActivity;
import com.hyperiongray.sitehound.backend.activity.dd.crawler.DdCrawlerOutputProgressActivity;
import com.hyperiongray.sitehound.backend.activity.dd.modeler.DdModelerOutputActivity;
import com.hyperiongray.sitehound.backend.activity.dd.trainer.DdTrainerOutputModelActivity;
import com.hyperiongray.sitehound.backend.activity.dd.trainer.DdTrainerOutputPagesActivity;
import com.hyperiongray.sitehound.backend.activity.dd.trainer.DdTrainerOutputProgressActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by tomas on 7/20/15.
 */
@SpringBootApplication
public class Application implements CommandLineRunner{
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired private KeywordsActivity keywordActivity;
	@Autowired private BroadcrawlerActivity broadcrawlerActivity;
	@Autowired private AquariumActivity aquariumActivity;
	@Autowired private ImportUrlActivity importUrlActivity;
	@Autowired private EventsActivity eventsActivity;
	@Autowired private DdModelerOutputActivity ddModelerOutputActivity;
	@Autowired private DdTrainerOutputModelActivity ddTrainerOutputModelActivity;
	@Autowired private DdTrainerOutputPagesActivity ddTrainerOutputPagesActivity;
	@Autowired private DdTrainerOutputProgressActivity ddTrainerOutputProgressActivity;
	@Autowired private DdCrawlerOutputPagesActivity ddCrawlerOutputPagesActivity;
	@Autowired private DdCrawlerOutputProgressActivity ddCrawlerOutputProgressActivity;

	public static void main(String args[]) {
		System.setProperty("jsse.enableSNIExtension", "false");
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		LOGGER.info("------------- Waiting for user input -------");

	}
}
