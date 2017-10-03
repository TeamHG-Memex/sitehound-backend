package com.hyperiongray.sitehound.backend.kafka.modeler;

import com.hyperiongray.sitehound.backend.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.Producer;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository;
import com.hyperiongray.sitehound.backend.service.dd.modeler.output.DdModelerOutputBrokerService;
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
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.verify;

/**
 * Created by tomas on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@SpringBootTest
public class ModelerOutputBrokerServiceTest {

    private static final String TEMPLATE_TOPIC = "dd-modeler-output";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TEMPLATE_TOPIC);

    @Autowired
    private Producer producer;

    @Autowired
    private DdModelerOutputBrokerService brokerService;

    @MockBean
    private ElasticsearchModelerModelRepository modelerModelRepositoryMock;

    @Test
    public void testTemplate(){

        String id = "59b114a2e4dc96629bb2b2fb";
        String quality = "b64-encoded page classifier model";
        String model = "b64 model";

        String input = "{" +
                "  \"workspace_id\": \"" + id + "\"," +
                "  \"quality\": \""+ quality +"\" ," +
                "  \"model\": \"" + model  + "\"" +
                "}";

        producer.produce(TEMPLATE_TOPIC, embeddedKafka, brokerService, input);

        // expecting repo to be called once with correct param
        DdModelerOutput ddModelerOutput = new DdModelerOutput();
        ddModelerOutput.setWorkspaceId(id);
        ddModelerOutput.setQuality(quality);
        ddModelerOutput.setModel(model);

        try {
            verify(modelerModelRepositoryMock).save(ddModelerOutput);
            ArgumentCaptor<DdModelerOutput> argument = ArgumentCaptor.forClass(DdModelerOutput.class);
            verify(modelerModelRepositoryMock).save(argument.capture());
            assertEquals(ddModelerOutput, argument.getValue());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

    }
}