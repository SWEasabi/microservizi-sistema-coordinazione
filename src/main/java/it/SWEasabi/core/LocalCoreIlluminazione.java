package it.SWEasabi.core;

import java.util.ArrayList;
import java.util.List;

import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;

public class LocalCoreIlluminazione implements IlluminationService {

	@Override
	public boolean setIlluminazione(long id, int value) {
		if(id>0&&value>0&&value<101) return true;
		return false;
	}

	@Override
	public LampAnagrafica getById(long id) {
		if(id>0) {
			LampAnagrafica lamp = new LampAnagrafica();
			lamp.setId(id);
			return lamp;
		}
		return null;
	}

	@Override
	public List<LampAnagrafica> getLampsInArea(long idArea) {
		if(idArea>0) {
			List<LampAnagrafica> list = new ArrayList<LampAnagrafica>();
			for(int i=1;i<11;++i) {
				LampAnagrafica lamp = new LampAnagrafica();
				lamp.setId(i);
				list.add(lamp);
			}
			return list;
		}
		return null;
	}

	@Override
	public AreaAnagrafica getAreaFromSensorId(long sensorId) {
		if(sensorId==1) return new AreaAnagrafica();
		if(sensorId==2) return new AreaAnagrafica(1, "area", false, 10, 20);
		if(sensorId>2) return new AreaAnagrafica(1, "area", true, 10, 20);
		return null;
	}

}
