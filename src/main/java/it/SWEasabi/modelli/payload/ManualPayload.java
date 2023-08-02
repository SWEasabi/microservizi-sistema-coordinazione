package it.SWEasabi.modelli.payload;

import java.util.ArrayList;
import java.util.List;

import it.SWEasabi.modelli.illuminazione.ModificaIlluminazione;

public class ManualPayload extends Payload
{
    private long idLamp;
    private int valore;

    public long getIdLampione() { return idLamp; }
    public int getValore() { return valore; }

    public ManualPayload(long _idLamp, int _valore)
    {
        idLamp = _idLamp;
        valore = _valore;
    }

    @Override
    void completePayload()
    {
        // controlla che i dati siano idonei
        status = PayloadStatus.Completed; // se qualcosa è andato storto, PayloadStatus.Error;
    }
    @Override
    public List<ModificaIlluminazione> analyze()
    {
        List<ModificaIlluminazione> modifiche = new ArrayList<>();
        modifiche.add(new ModificaIlluminazione(idLamp, valore));
        return modifiche;
    }
}
