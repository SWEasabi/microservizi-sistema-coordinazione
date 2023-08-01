package it.SWEasabi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.SWEasabi.core.CoreIlluminazione;

@RestController
public class Controller {
	
	@Autowired
	CoreIlluminazione coreIlluminazione;
	
	@GetMapping("/setIlluminazione/{idLamp}/{value}")
	public boolean SetIlluminazione(@PathVariable long idLamp, @PathVariable int value)
	{
		return coreIlluminazione.setIlluminazione(idLamp, value);
	}
}
