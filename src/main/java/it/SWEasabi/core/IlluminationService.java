package it.SWEasabi.core;

import java.util.List;

import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;

public interface IlluminationService {
	public boolean setIlluminazione(long id, int value);
	public LampAnagrafica getById(long id);
	public List<LampAnagrafica> getLampsInArea(long idArea);
	public AreaAnagrafica getAreaFromSensorId(long sensorId);
}
