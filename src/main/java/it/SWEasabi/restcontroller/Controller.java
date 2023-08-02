package it.SWEasabi.restcontroller;

import java.awt.geom.Area;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.SWEasabi.core.CoreIlluminazione;
import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;

@RestController
public class Controller {
	
	@Autowired
	CoreIlluminazione coreIlluminazione;
	
	@PostMapping("/setIlluminazione/{idLamp}/{value}")
	public boolean SetIlluminazione(@PathVariable long idLamp, @PathVariable int value)
	{
		return coreIlluminazione.setIlluminazione(idLamp, value);
	}
	
	@GetMapping("lamp/{id}")
	public LampAnagrafica getLamp(@PathVariable long id)
	{
		return coreIlluminazione.getById(id);
	}
	
	@GetMapping("getLamps/{idArea}")
	public List<LampAnagrafica> getLampsInArea(@PathVariable long idArea)
	{
		return coreIlluminazione.getLampsInArea(idArea);
	}
	
	@GetMapping("getArea/{idSensore}")
	public AreaAnagrafica getAreaFromSensorId(@PathVariable long idSensore){
		return coreIlluminazione.getAreaFromSensorId(idSensore);
	}
}
