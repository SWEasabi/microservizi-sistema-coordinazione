package it.SWEasabi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.SWEasabi.core.CoreIlluminazione;
import it.SWEasabi.modelli.payload.PayloadQueue;
import it.SWEasabi.mqtt.MqttController;
import it.SWEasabi.coordinazione.Consumer;
import it.SWEasabi.coordinazione.Producer;

@Configuration
public class BeanConfig {
	
	/*
	@Bean
	CoreIlluminazione getCoreIlluminazione() {
		return new CoreIlluminazione();
	}
	*/

	@Bean
	MqttController getMqttController()
	{
		PayloadQueue payloadQueue = new PayloadQueue();
		Consumer consumer = new Consumer(payloadQueue);
		Producer producer = new Producer(payloadQueue);
		return new MqttController(consumer, producer);
	}
}
