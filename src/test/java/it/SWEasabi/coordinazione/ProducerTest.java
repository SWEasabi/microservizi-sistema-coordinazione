package it.SWEasabi.coordinazione;

import it.SWEasabi.core.CoreIlluminazione;
import it.SWEasabi.repositories.illuminazione.AreaRepository;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class ProducerTest {

    @Autowired
    private Producer producer;

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private CoreIlluminazione coreIlluminazione;

    @BeforeEach
    public void setUp() {
        producer.setMqttClient(mqttClient);
        producer.setCore(coreIlluminazione);
    }

    @Test
    public void testOnSensorMessageReceived() throws Exception {
        long idSensore = 123;
        int stato = 1;

        producer.OnSensorMessageReceived(idSensore, stato);

        MqttMessage loggingResponse = new MqttMessage();
        String str = "{'id':123, valore':1, 'tipo': 'sensore'}";
        loggingResponse.setPayload(str.getBytes(StandardCharsets.UTF_8));
        verify(mqttClient, times(1)).publish("logging", loggingResponse);
    }
}
