package com.hyperiongray.framework;

//import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

public class JsonMapper<T> {

    private ObjectMapper mapper = new ObjectMapper();

//	@Deprecated
//	public SubscriberInput toObject(String jsonSubscriber) throws IOException {
//        SubscriberInput subscriberInput = mapper.readValue(jsonSubscriber, SubscriberInput.class);
//        return subscriberInput;
//    }


    public T toObject(String content, Class<T> clazz) throws IOException {
        return mapper.readValue(content, clazz);
    }

	public String toJson(T content) throws IOException {
		return mapper.writeValueAsString(content);
	}

    public T fromJSON(final String jsonPacket, final Class<T> type) throws IOException {
            return mapper.readValue(jsonPacket, type);
    }

}
