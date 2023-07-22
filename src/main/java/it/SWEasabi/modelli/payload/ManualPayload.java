package it.SWEasabi.modelli.payload;

import java.util.ArrayList;

import it.SWEasabi.modelli.illuminazione.LampIlluminazione;

public class ManualPayload extends Payload
{
    private int idLamp, valore;
    private long timestamp;

    public int getIdLampione() { return idLamp; }
    public int getValore() { return valore; }
    public long getTimeStamp() { return timestamp; }

    public ManualPayload(int _idLamp, int _valore, long _timestamp)
    {
        idLamp = _idLamp;
        valore = _valore;
        timestamp = _timestamp;
    }

    @Override
    void completePayload()
    {
        // controlla che i dati siano idonei
        status = PayloadStatus.Completed; // se qualcosa è andato storto, PayloadStatus.Error;
    }
    @Override
    public ArrayList<LampIlluminazione> analyze()
    {
        ArrayList<LampIlluminazione> modifiche = new ArrayList<>();
        modifiche.add(new LampIlluminazione(idLamp, valore, timestamp));
        return modifiche;
    }
}
