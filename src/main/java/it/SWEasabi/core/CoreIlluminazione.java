package it.SWEasabi.core;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.SWEasabi.modelli.anagrafica.LampAnagrafica;
import it.SWEasabi.modelli.logging.Log;
import it.SWEasabi.repositories.illuminazione.LampAnagraficaRepository;
import it.SWEasabi.repositories.logging.LoggingRepository;
import jakarta.transaction.Transactional;

public class CoreIlluminazione {
	
	@Autowired
	LampAnagraficaRepository lampRepo;
	
	@Autowired
	LoggingRepository logRepo;
	
	@Transactional
	public boolean setIlluminazione(long id, int value) {
		try {
			LampAnagrafica lamp = lampRepo.findById(id);
			lamp.setLuminosita(value);
			lampRepo.save(lamp);
			Log temp = new Log(lamp.getId(),Instant.now().toEpochMilli()/1000,value, "lampione");
			logRepo.save(temp);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public LampAnagrafica getById(long id)
	{
		return lampRepo.findById(id);
	}
	
	/*public List<LampAnagrafica> getLampsInArea(long idArea)
	{
		return lampRepo.findByIdarea(idArea);
	}*/
}
