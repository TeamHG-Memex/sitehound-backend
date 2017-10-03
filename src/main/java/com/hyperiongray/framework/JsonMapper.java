package com.hyperiongray.framework;

import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

public class JsonMapper<T> {

    private ObjectMapper mapper = new ObjectMapper();

	@Deprecated
	public SubscriberInput toObject(String jsonSubscriber) throws IOException {
        SubscriberInput subscriberInput = mapper.readValue(jsonSubscriber, SubscriberInput.class);
        return subscriberInput;
    }

//	@Deprecated
//	public String toJson(CrawlResult crawlResult) throws IOException {
//        String json = mapper.writeValueAsString(crawlResult);
//        return json;
//    }

    public T toObject(String content, Class<T> clazz) throws IOException {
        return mapper.readValue(content, clazz);
    }

	public String toString(T content) throws IOException {
		return mapper.writeValueAsString(content);
	}

    public <T> T fromJSON(final String jsonPacket, final TypeReference<T> type) throws IOException {
            return mapper.readValue(jsonPacket, type);
    }

}
