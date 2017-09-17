package com.hyperiongray.sitehound.backend.integration;

import com.hyperiongray.sitehound.backend.config.Configuration;
import com.hyperiongray.sitehound.backend.integration.mocks.ImportUrlProducerMock;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Calendar;

import static junit.framework.TestCase.fail;

/**
 * Created by tomas on 2/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class ImportUrlFlowTest{

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportUrlFlowTest.class);

	@Autowired
	ImportUrlProducerMock importUrlProducerMock;

	@Test
	public void importUrlFlowTest(){

		String url = "https://en.wikipedia.org/wiki/guns_%2526_ammo?"+ Calendar.getInstance().getTimeInMillis();
		Metadata metadata = IntegrationHelper.getMetadata();
		try{
			importUrlProducerMock.submit(metadata,url);
		}catch(IOException e){
			LOGGER.error("FAILED",e);
			fail();
		}
		try{
			System.out.println("sleeping");
			Thread.sleep(60*1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}


	public static class ImportUrl{
		private final Metadata metadata;
		private final String url;
		private final Boolean relevant;

		public ImportUrl(Metadata metadata, String url, Boolean relevant){

			this.metadata = metadata;
			this.url = url;
			this.relevant = relevant;
		}

		public Metadata getMetadata(){
			return metadata;
		}

		public String getUrl(){
			return url;
		}

		public Boolean getRelevant(){
			return relevant;
		}

	}

}
