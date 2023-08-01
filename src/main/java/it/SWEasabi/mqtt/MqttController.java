package it.SWEasabi.mqtt;

import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import it.SWEasabi.coordinazione.Consumer;
import it.SWEasabi.coordinazione.Producer;
import it.SWEasabi.modelli.payload.PayloadQueue;

@RestController
public class MqttController implements MqttCallback
{
    private MqttClient client;

    private PayloadQueue payloadQueue;
    private Consumer consumer;
    private Producer producer;
    private final String topicLampioni;

    public MqttController()
    {
        // bean
        payloadQueue = new PayloadQueue();
        consumer = new Consumer(payloadQueue);
        producer = new Producer(payloadQueue);

        // mqtt
        setupMqtt();
        topicLampioni = "lampione";

        // run consumer
        consumer.run();
    }

    public void setupMqtt()
    {
        try
        {
            client = new MqttClient("tcp://localhost:1883", "Client");
            client.connect();
            client.setCallback(this);
            client.subscribe("sensore");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token)
    {
        // delivery complete
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception
    {
        // è arrivato un messaggio da un sensore
        try
        {
            JsonObject rq = new Gson().fromJson(message.toString(), JsonObject.class);
            int idSensore = Integer.parseInt(rq.get("idSensore").toString());
            int stato = Integer.parseInt(rq.get("stato").toString());
            long timestamp = Long.parseLong(rq.get("timestamp").toString());

            //producer.OnSensorMessageReceived(idSensore, stato, timestamp);

            MqttMessage response = new MqttMessage();
            String str = "risposta ciao ciao";
            response.setPayload(str.getBytes(StandardCharsets.UTF_8));
            client.publish(topicLampioni, response);

            /*
            System.out.println("Topic:" +  topic);
            System.out.println("idSensore:" + idSensore);
            System.out.println("stato:" + stato);
            System.out.println("timestamp:" + timestamp);
            */
        }
        catch(Exception e)
        {
            System.out.println("Wrong json");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping("/addLamps")
    public void newLamp(@RequestBody int prova)
    {
        
    }
}
