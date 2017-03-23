package com.hyperiongray.sitehound.backend.service.splash;

/**
 * Created by tomas on 7/3/15.
 */
public class SplashBrokerTask{}
/*
implements Runnable{


	private JsonMapper<SplashInput> splashInputJsonMapper = new JsonMapper();
	private JsonMapper<SplashOutput> splashOutputJsonMapper = new JsonMapper();

	private Splash splash;
	private Semaphore splashTaskSemaphore;
	private ProducerWrapper producer;
	private String jsonInput;

	private static final Logger LOGGER = LoggerFactory.getLogger(SplashBrokerTask.class);

	public SplashBrokerTask(Splash splash, Semaphore splashTaskSemaphore, ProducerWrapper producer, String jsonInput) {
		this.splash=splash;
		this.splashTaskSemaphore = splashTaskSemaphore;
		this.producer = producer;
		this.jsonInput = jsonInput;
	}

	@Override
	public void run() {
		try {
			LOGGER.debug("Ready to splash: " + jsonInput);
//			splashTaskSemaphore.acquire();
			SplashInput splashInput = splashInputJsonMapper.toObject(jsonInput, SplashInput.class);
			SplashOutput splashOutput = execute(splashInput);
			String splashOutputString = splashOutputJsonMapper.toString(splashOutput);
			LOGGER.debug("Sending splashed");
			LOGGER.trace("Sending splashed: " + splashOutputString);
			producer.send(splashOutputString);
			LOGGER.debug("Finishing splashing");
			LOGGER.trace("Finishing splashing: " + splashOutputString);
		} catch (Exception e) {
			LOGGER.error("BROKER_ERROR", e);
		}
		finally {
			splashTaskSemaphore.release();
			LOGGER.info("Current Splash semaphores: " + splashTaskSemaphore.availablePermits());
		}
	}

	public SplashOutput execute(SplashInput splashInput) throws java.io.IOException{
		String snapshot = splash.snapshot(splashInput.getUrl());
		SplashOutput splashOutput = new SplashOutput();
		splashOutput.setWorkspace(splashInput.getWorkspace());
		splashOutput.setUrl(splashInput.getUrl());
		splashOutput.setCollection(splashInput.getCollection());
		splashOutput.setSnapshot(snapshot);
		return splashOutput;
	}

}
*/