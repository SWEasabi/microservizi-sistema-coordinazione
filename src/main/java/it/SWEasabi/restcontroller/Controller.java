package it.SWEasabi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.SWEasabi.mqtt.MqttController;

@RestController
public class Controller {

	@Autowired
	MqttController mqttController;
	
	@PostMapping("/setIlluminazione/{idLamp}/{value}")
	public void SetIlluminazione(@PathVariable long idLamp, @PathVariable int value)
	{
		mqttController.apiArrived(idLamp, value);
	}
}
