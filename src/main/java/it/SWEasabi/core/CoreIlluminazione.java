package it.SWEasabi.core;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;
import it.SWEasabi.modelli.anagrafica.Misuratore;
import it.SWEasabi.modelli.anagrafica.SensoreAnagrafica;
import it.SWEasabi.modelli.logging.Log;
import it.SWEasabi.repositories.illuminazione.AreaRepository;
import it.SWEasabi.repositories.illuminazione.LampAnagraficaRepository;
import it.SWEasabi.repositories.illuminazione.MisuratoreRepository;
import it.SWEasabi.repositories.illuminazione.SensoreRepository;
import it.SWEasabi.repositories.logging.LoggingRepository;
import jakarta.transaction.Transactional;

public class CoreIlluminazione {
	
	@Autowired
	LampAnagraficaRepository lampRepo;
	
	@Autowired
	MisuratoreRepository misRepo;
	
	@Autowired
	LoggingRepository logRepo;
	
	@Autowired
	SensoreRepository sensorRepo;
	
	@Autowired
	AreaRepository areaRepo;
	
	public boolean setIlluminazione(long id, int value) {
		try
		{
			LampAnagrafica lamp = lampRepo.findById(id);
			lamp.setLuminosita(value);
			lampRepo.save(lamp);
			//Log temp = new Log(lamp.getId(),Instant.now().toEpochMilli()/1000,value, "lampione");
			//logRepo.save(temp);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/*
	public boolean setLogSensore(long id, int value)
	{
		try
		{
			Log temp = new Log(id, Instant.now().toEpochMilli()/1000, value, "sensore");
			logRepo.save(temp);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	*/
	
	public LampAnagrafica getById(long id)
	{
		return lampRepo.findById(id);
	}
	
	public List<LampAnagrafica> getLampsInArea(long idArea)
	{
		List<Misuratore> list = misRepo.findByIdareaAndTipo(idArea, "lampione");
		List<LampAnagrafica> lamps = new ArrayList<LampAnagrafica>();
		for(Misuratore m : list) {
			LampAnagrafica lamp = m.getLampione();
			lamp.getMisuratore().setLampione(null);
			lamps.add(lamp);
		}
		return lamps;
	}
	
	public AreaAnagrafica getAreaFromSensorId(long sensorId) {
		SensoreAnagrafica sensore = sensorRepo.findById(sensorId);
		long idArea = sensore.getMisuratore().getIdArea();
		return areaRepo.findById(idArea);
	}
}
