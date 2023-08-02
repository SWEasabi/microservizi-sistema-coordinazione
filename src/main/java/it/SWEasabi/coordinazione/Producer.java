package it.SWEasabi.coordinazione;

import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import it.SWEasabi.modelli.payload.AutoPayload;
import it.SWEasabi.modelli.payload.ManualPayload;
import it.SWEasabi.modelli.payload.PayloadQueue;
import it.SWEasabi.modelli.payload.PayloadThread;

public class Producer
{
    private MqttClient client;
    private final PayloadQueue payloadQueue;
    private final String topicLogging;
    public Producer(PayloadQueue _payloadQueue)
    {
        payloadQueue = _payloadQueue;
        topicLogging = "logging";
    }
    public void setMqttClient(MqttClient _client)
    {
        client = _client;
    }
    public void OnSensorMessageReceived(long idSensore, int stato)
    {
        // mando al sistema di logging
        try
        {
            MqttMessage loggingResponse = new MqttMessage();
            String str2 = "{'id':" + idSensore + ", valore':" + stato + ", 'tipo': 'sensore'}";
            loggingResponse.setPayload(str2.getBytes(StandardCharsets.UTF_8));
            client.publish(topicLogging, loggingResponse);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        PayloadThread payload = new PayloadThread(new AutoPayload(idSensore, idSensore));
        payloadQueue.add(payload);
        payloadQueue.notifyAllForEmpty();
    }
    public void OnApiRequest(long idLampione, int valore)
    {
        PayloadThread payload = new PayloadThread(new ManualPayload(idLampione, valore));
        payloadQueue.add(payload);
        payloadQueue.notifyAllForEmpty();
    }
}
