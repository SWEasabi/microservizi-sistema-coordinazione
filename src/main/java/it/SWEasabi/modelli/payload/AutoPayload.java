package it.SWEasabi.modelli.payload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.SWEasabi.core.CoreIlluminazione;
import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;
import it.SWEasabi.modelli.illuminazione.ModificaIlluminazione;

public class AutoPayload extends Payload
{
    @Autowired
    CoreIlluminazione coreIlluminazione;
    private long idSensore, statoSensore;
    private AreaAnagrafica area;
    private List<LampAnagrafica> lamps;

    public long getIdSensore() { return idSensore; }
    public long getStatoSensore() { return statoSensore; }
    public AreaAnagrafica getArea() { return area; }
    public List<LampAnagrafica> getLamps() { return lamps; }

    public AutoPayload(long _idSensore, long _statoSensore)
    {
        idSensore = _idSensore;
        statoSensore = _statoSensore;
        area = null;
        lamps = null;
    }
    @Override
    void completePayload()
    {
        // chiedo all'anagrafica i dati dell'area in cui è il sensore
        area = coreIlluminazione.getAreaFromSensorId(idSensore);
        if(area == null)
        {
            status = PayloadStatus.Error;
        }
        else
        {
            // chiedo all'anagrafica i dati dei lampioni in quell'area
            lamps = coreIlluminazione.getLampsInArea(area.getId());
            if(lamps == null)
            {
                status = PayloadStatus.Error;
            }
            else
            {
                // ho completato il payload
                status = PayloadStatus.Completed; // se qualcosa è andato storto, PayloadStatus.Error;
            }
        }
    }
    @Override
    public List<ModificaIlluminazione> analyze()
    {
        List<ModificaIlluminazione> modifiche = new ArrayList<ModificaIlluminazione>();
        if(area.getModAutomatica()) // se sono in modalità automatica
        {
            int nuovoValore = statoSensore == 0 ? area.getLvlInf() : area.getLvlSup();
            for(LampAnagrafica lampAnagrafica : lamps)
            {
                modifiche.add(new ModificaIlluminazione(lampAnagrafica.getId(), nuovoValore));
            }
        }
        return modifiche;
    }
}
