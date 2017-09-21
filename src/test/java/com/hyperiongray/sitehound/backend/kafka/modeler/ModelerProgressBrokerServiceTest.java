package com.hyperiongray.sitehound.backend.kafka.modeler;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerProgress;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdModelerProgressRepository;
import com.hyperiongray.sitehound.backend.service.dd.modeler.output.DdModelerProgressBrokerService;
import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.Producer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@SpringBootTest
public class ModelerProgressBrokerServiceTest {

    private static final String TEMPLATE_TOPIC = "dd-modeler-progress";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private Producer producer;

    @Autowired
    private DdModelerProgressBrokerService brokerService;

    @MockBean
    private DdModelerProgressRepository ddModelerProgressRepositoryMock;

    @Test
    public void testTemplate(){

        String id = "59c27d45e91b2e11edbaf859";
        Double percentageDone = 98.123;

        String input = "{" +
                "    \"workspace_id\":\"" + id + "\"," +
                "    \"percentage_done\":" + percentageDone + " " +
                "}";
        producer.produce(TEMPLATE_TOPIC, embeddedKafka, brokerService, input);

        // expecting repo to be called once with correct param
        DdModelerProgress ddModelerProgress = new DdModelerProgress();
        ddModelerProgress.setWorkspaceId(id);
        ddModelerProgress.setPercentageDone(percentageDone);

        verify(ddModelerProgressRepositoryMock).saveProgress(ddModelerProgress);

        ArgumentCaptor<DdModelerProgress> argument = ArgumentCaptor.forClass(DdModelerProgress.class);
        verify(ddModelerProgressRepositoryMock).saveProgress(argument.capture());
        assertEquals(ddModelerProgress, argument.getValue());

    }

}