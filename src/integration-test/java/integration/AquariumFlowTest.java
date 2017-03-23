package integration;

import com.hyperiongray.sitehound.backend.Configuration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium.AquariumInput;
import com.hyperiongray.sitehound.backend.kafka.producer.AquariumProducer;
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
public class AquariumFlowTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(AquariumFlowTest.class);

	@Autowired
	AquariumProducer aquariumProducer;


	@Test
	public void aquariumFlowTest(){

		String url = "https://en.wikipedia.org/wiki/guns_%2526_ammo?"+ Calendar.getInstance().getTimeInMillis();
		Metadata metadata = IntegrationHelper.getMetadata();
		try{
			AquariumInput aquariumInput = new AquariumInput(metadata);
			aquariumInput.setUrl(url);
			aquariumInput.setIndex(100);
			aquariumProducer.submit(aquariumInput);
		}catch(IOException e){
			fail();
			e.printStackTrace();
		}

		try{
			System.out.println("sleeping");
			Thread.sleep(60*1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
