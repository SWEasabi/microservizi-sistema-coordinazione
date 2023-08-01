package it.SWEasabi.core;

import org.springframework.beans.factory.annotation.Autowired;

import it.SWEasabi.modelli.anagrafica.LampAnagrafica;
import it.SWEasabi.repositories.LampAnagraficaRepository;

public class CoreIlluminazione {
	
	@Autowired
	LampAnagraficaRepository lampRepo;
	
	public boolean setIlluminazione(long id, int value) {
		try {
			LampAnagrafica lamp = lampRepo.findById(id);
			lamp.setLuminosita(value);
			lampRepo.save(lamp);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
