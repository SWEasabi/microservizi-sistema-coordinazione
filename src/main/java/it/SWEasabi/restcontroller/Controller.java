package it.SWEasabi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.SWEasabi.mqtt.MqttController;

@RestController
public class Controller {

	private MqttController mqttController;
	
	public Controller(MqttController mqttController) {
		this.mqttController=mqttController;
	}
	
	@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600)
	@PostMapping("/setIlluminazione/{idLamp}/{value}")
	public boolean SetIlluminazione(@PathVariable long idLamp, @PathVariable int value)
	{
		try {
			mqttController.apiArrived(idLamp, value);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
