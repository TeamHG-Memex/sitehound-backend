package com.hyperiongray.framework;

import com.google.common.collect.Lists;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberOutput;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonMapperTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapperTest.class);

    @Test
    public void toObject() throws Exception {
    }

    @Test
    public void toObject1() throws Exception {
    }

    @Test
    public void toString1() throws Exception {
    }

    @Test
    public void fromJSON() throws Exception {
    }


    @Test
    public void testJsonMapper() throws IOException {

        SubscriberOutput subscriberOutput = new SubscriberOutput();
        subscriberOutput.setDesc("descriptions");
        subscriberOutput.setHost("google.com");
        subscriberOutput.setUrl("http://www.google.com/searchapi/adfadñffk");
        subscriberOutput.setUrlDesc("the link desc");
        subscriberOutput.setWorkspace("workspace fifa");
        subscriberOutput.setScore(100);
        subscriberOutput.setWorkspace("fifa");
        subscriberOutput.setWords(Lists.newArrayList("word1", "word2", "word3", "word4", "word5"));

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.toString(subscriberOutput);
        LOGGER.info("To JSON: " + json);

        String message = "{" +
                "\"included\": [\"pos1\", \"pos2\", \"pos4\"]," +
                "\"excluded\": [\"neg1\", \"neg\", \"neg\"]," +
                "\"source\": \"self.app_instance\"," +
                "\"callbackQueue\": \"self.KEYWORDS_TOPIC_OUTPUT\"," +
                "\"timestamp\": 56545656565," +
                "\"nResults\": 20," +
                "\"workspace\": \"fifa\"," +
                "\"strTimestamp\": \"2015-04-03 22:22:02\"," +
                "\"relevantUrl\": [\"http://www.filmaffinity.com/es/film620090.html\", \"http://www.imdb" +
                ".com/title/tt1392190/\"]," +
                "\"irrelevantUrl\": [\"http://stackoverflow.com/\", \"https://es.wikipedia.org/wiki/Mad_Max\"]"+
                "}";
        SubscriberInput subscriberInput = jsonMapper.toObject(message);
        LOGGER.info(subscriberInput.toString());
    }
}


