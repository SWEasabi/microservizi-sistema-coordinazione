package it.SWEasabi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.SWEasabi.core.CoreIlluminazione;
import it.SWEasabi.mqtt.MqttController;

@RestController
public class Controller {
	
	//@Autowired
	//CoreIlluminazione coreIlluminazione;

	@Autowired
	MqttController mqttController;
	
	/*@PostMapping("/start")
	public boolean Test(){
		try {
			
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}*/
	
	@GetMapping("/test")
	public String test(){
		return "cucu";
	}
	
	@PostMapping("/setIlluminazione/{idLamp}/{value}")
	public boolean SetIlluminazione(@PathVariable long idLamp, @PathVariable int value)
	{
		return false;
		//mqttController.apiArrived(idLamp, value);
		//return coreIlluminazione.setIlluminazione(idLamp, value);
	}
}
