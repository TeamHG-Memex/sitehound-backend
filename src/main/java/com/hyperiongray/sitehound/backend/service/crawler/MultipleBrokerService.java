package com.hyperiongray.sitehound.backend.service.crawler;

/**
 * Created by tomas on 7/8/15.
 */
public class MultipleBrokerService{}
/* implements BrokerService {

	private List<BrokerService> brokerServices = new LinkedList<>();
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	private static final Logger LOGGER = LoggerFactory.getLogger(MultipleBrokerService.class);

	@Override
	public void getDdTrainerInputStart(String jsonInput, Semaphore semaphore){
		for(BrokerService brokerService: brokerServices){
			executorService.submit(new DispatcherWorker(brokerService, jsonInput, semaphore));
		}
	}

	public void addBrokerService(BrokerService brokerService){
		this.getBrokerServices().add(brokerService);
	}


	public List<BrokerService> getBrokerServices() {
		if(brokerServices==null){
			brokerServices = new LinkedList<>();
		}
		return brokerServices;
	}

	private class Worker implements Runnable{

		private BrokerService brokerService;
		private final String jsonInput;
		private final Semaphore semaphore;
		private ProducerWrapper producer;

		public Worker(BrokerService brokerService, String jsonInput, Semaphore semaphore, ProducerWrapper producer){
			this.brokerService=brokerService;
			this.jsonInput=jsonInput;
			this.semaphore=semaphore;
			this.producer=producer;
		}

		@Override
		public void run(){
			LOGGER.info("processing message for brokerService: " + brokerService);
			brokerService.getDdTrainerInputStart(jsonInput, semaphore, producer);
		}
	}

}
*/