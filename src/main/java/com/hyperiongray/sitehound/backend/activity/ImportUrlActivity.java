package com.hyperiongray.sitehound.backend.activity;

import com.hyperiongray.sitehound.backend.kafka.api.dto.importurl.ImportUrlInput;
import com.hyperiongray.sitehound.backend.service.ImportUrlListenerService;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 11/5/15.
 */
@Component
public class ImportUrlActivity {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImportUrlActivity.class);

	@Autowired private ImportUrlListenerService importUrlBrokerService;

	private final JsonMapper<ImportUrlInput> importUrlInputJsonMapper = new JsonMapper();

	@KafkaListener(topics= "import-url-input")
	public void listen(@Payload String data,
//					   @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
					   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
					   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

//		LOGGER.debug("received data:" + data + ", key:" + key + "; partition:" + partition + ", topic:" + topic);
		LOGGER.debug("received data:" + data + ", partition:" + partition + ", topic:" + topic);
		try {
			ImportUrlInput input = importUrlInputJsonMapper.toObject(data, ImportUrlInput.class);
			importUrlBrokerService.process(input);
		}catch(IOException e){
			LOGGER.error("ERROR PROCESSING: " +  data, e);
		}
	}

}
