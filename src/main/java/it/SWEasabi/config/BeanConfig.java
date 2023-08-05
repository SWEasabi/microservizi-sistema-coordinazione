package it.SWEasabi.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.SWEasabi.modelli.payload.PayloadQueue;
import it.SWEasabi.mqtt.MqttController;
import it.SWEasabi.coordinazione.Consumer;
import it.SWEasabi.coordinazione.Producer;
import it.SWEasabi.core.CoreIlluminazione;

@Configuration
public class BeanConfig {
	
	@Bean
	CoreIlluminazione getCoreIlluminazione() {
		return new CoreIlluminazione();
	}
	
	@Bean
	MqttClient getMqttClient() {
        try
        {
        	MemoryPersistence memory = new MemoryPersistence();
            MqttClient client = new MqttClient("tcp://localhost:1883", "Client", memory);
            client.connect();
            client.subscribe("sensore");
            return client;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}
	@Bean
	PayloadQueue getPayloadQueue() {
		return new PayloadQueue();
	}
	@Bean
	Producer getProducer() {
		Producer producer = new Producer(getPayloadQueue());
		return producer;
	}
	
	@Bean
	Consumer getConsumer() {
		Consumer consumer = new Consumer(getPayloadQueue());
		return consumer;
	}

	@Bean
	MqttController getMqttController()
	{
		MqttController mqttController = new MqttController();
		mqttController.setConsumer(getConsumer());
		mqttController.setProducer(getProducer());
		MqttClient client = getMqttClient();
		mqttController.setMqttClient(client);
		client.setCallback(mqttController);
		mqttController.getConsumer().setMqttClient(client);
		mqttController.getProducer().setMqttClient(client);
		CoreIlluminazione core = getCoreIlluminazione();
		mqttController.getProducer().setCore(core);
		mqttController.getConsumer().setCore(core);
		Thread consumerThread = new Thread(mqttController.getConsumer());
		consumerThread.start();
		return mqttController;
	}
}
