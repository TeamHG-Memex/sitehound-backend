package com.hyperiongray.sitehound.backend.service.splash;

import org.springframework.stereotype.Service;

/**
 * Created by tomas on 5/24/15.
 */
@Service
public class SplashBrokerService{}
/*implements BrokerService{

	@Autowired
	private Splash splash;

	@Value( "${splash.threads}" ) private int threadNumber;

	private ExecutorService executorService;


	@PostConstruct
	public void init(){
		executorService=Executors.newFixedThreadPool(threadNumber);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SplashBrokerService.class);

	@Override
	public void getDdTrainerInputStart(String jsonInput, Semaphore semaphore){
		LOGGER.info("ready to splash:" + jsonInput);
		executorService.submit(new SplashBrokerTask(splash, semaphore, producer, jsonInput));
		LOGGER.info("splashed submitted");
	}

}
*/