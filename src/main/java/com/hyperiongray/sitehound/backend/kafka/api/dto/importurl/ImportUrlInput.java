package com.hyperiongray.sitehound.backend.kafka.api.dto.importurl;

/**
 * Created by tomas on 11/5/15.
 */

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
	 {
        "url": "http://www.fravega.com",
		 "isRelevant": true,
        "metadata": {
            "source": "bd81fed6-83d0-11e5-ae33-48d2241b1e46",
            "strTimestamp": "2015-11-05 15:20:27",
            "callbackQueue": "callback_queue_not_used",
            "workspace": "cats",
            "timestamp": 1446736827.420937}
    }
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportUrlInput extends KafkaDto {

	private String url;
	private Boolean isRelevant;
	private Metadata metadata;

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public Boolean getIsRelevant(){
		return isRelevant;
	}

	public void setIsRelevant(Boolean isRelevant){
		this.isRelevant=isRelevant;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setMetadata(Metadata metadata){
		this.metadata=metadata;
	}

	@Override
	public String toString(){
		return "ImportUrlInput{" +
				       "url='" + url + '\'' +
				       ", isRelevant=" + isRelevant +
				       ", metadata=" + metadata +
				       '}';
	}
}
