package it.SWEasabi.modelli.payload;

import java.util.ArrayList;

import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;
import it.SWEasabi.modelli.illuminazione.LampIlluminazione;

public class AutoPayload extends Payload
{
    private long timestamp;
    private int idSensore, statoSensore;
    private AreaAnagrafica area;
    private ArrayList<LampAnagrafica> lamps;

    public int getIdSensore() { return idSensore; }
    public int getStatoSensore() { return statoSensore; }
    public long getTimeStamp() { return timestamp; }
    public AreaAnagrafica getArea() { return area; }
    public ArrayList<LampAnagrafica> getLamps() { return lamps; }

    public AutoPayload(int _idSensore, int _statoSensore, long _timestamp)
    {
        idSensore = _idSensore;
        statoSensore = _statoSensore;
        timestamp = _timestamp;
        area = null;
        lamps = null;
    }
    @Override
    void completePayload()
    {
        // chiedo all'anagrafica i dati dell'area in cui è il sensore con una chiamata API
        // chiedo all'anagrafica i dati dei lampioni in quell'area con una chiamata API

        // ho completato il payload

        status = PayloadStatus.Completed; // se qualcosa è andato storto, PayloadStatus.Error;
    }
    @Override
    public ArrayList<LampIlluminazione> analyze()
    {
        ArrayList<LampIlluminazione> modifiche = new ArrayList<>();
        if(area.getModAutomatica()) // se sono in modalità automatica
        {
            int nuovoValore = statoSensore == 0 ? area.getLvlInf() : area.getLvlSup();
            for(LampAnagrafica lampAnagrafica : lamps)
            {
                modifiche.add(new LampIlluminazione(lampAnagrafica.getId(), nuovoValore, timestamp));
            }
        }
        return modifiche;
    }
}
