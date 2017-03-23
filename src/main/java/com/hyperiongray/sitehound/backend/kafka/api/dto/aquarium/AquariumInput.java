package com.hyperiongray.sitehound.backend.kafka.api.dto.aquarium;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 8/23/15.
 */

/**
    {"metadata":{"provider":"HH_JOOGLE","source":"91b050b6-5ee9-11e5-b425-0242ac11000e","timestamp":1442688278,"nResults":10,"crawlType":"KEYWORDS","strTimestamp":"2015-09-19 18:44:38","workspace":"test1","callbackQueue":"google-keywords-output","taskId":null,"uow":null,"crawlEntityType":"TWITTER"},
    "url":"https://twitter.com/_2ALIFE/status/645286553770524672",
    "index":9}
 */

public class AquariumInput extends KafkaDto {

	private Metadata metadata;
	private String url;
	private Integer index;

	@JsonCreator
	public AquariumInput(@JsonProperty("metadata") Metadata metadata){
		super();
		this.metadata=metadata;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public Integer getIndex(){
		return index;
	}

	public void setIndex(Integer index){
		this.index=index;
	}


	public Metadata getMetadata(){
		return metadata;
	}


	@Override
	public String toString(){
		return "AquariumInput{" +
				       "metadata=" + metadata +
				       ", url='" + url + '\'' +
				       ", index=" + index +
					       '}';
	}
}