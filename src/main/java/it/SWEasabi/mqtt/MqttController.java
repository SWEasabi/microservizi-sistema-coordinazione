package it.SWEasabi.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import it.SWEasabi.coordinazione.Consumer;
import it.SWEasabi.coordinazione.Producer;

@Component
public class MqttController implements MqttCallback
{
    private MqttClient client;

    private Consumer consumer;
    private Producer producer;
    
    public MqttController(MqttClient client, Consumer consumer, Producer producer)
    {
    	this.client=client;
        this.consumer = consumer;
        this.producer = producer;
        
        client.setCallback(this);

        // mqtt
        //setupMqtt();

        // run consumer
        producer.setMqttClient(client);
        consumer.setMqttClient(client);
        Thread consumerThread = new Thread(consumer);
		consumerThread.start();
    }
    /*public void StartConsumer() {
    	consumer.run();
    }
    public void setConsumer(Consumer consumer) {
    	this.consumer=consumer;
    }
    public void setProducer(Producer producer) {
    	this.producer=producer;
    }*/
    public void setMqttClient(MqttClient client) {
    	this.client=client;
    }
    public Producer getProducer() {
    	return producer;
    }
    public Consumer getConsumer() {
    	return consumer;
    }
    public MqttClient getClient() {
    	return client;
    }

    /*public void setupMqtt()
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
    }*/

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

            producer.OnSensorMessageReceived(idSensore, stato);
        }
        catch(Exception e)
        {
            System.out.println("Wrong json");
        }
    }
    public void apiArrived(long idLampione, int value)
    {
        producer.OnApiRequest(idLampione, value);
    }
}
