package it.SWEasabi.modelli.payload;

import java.util.List;

import it.SWEasabi.modelli.illuminazione.ModificaIlluminazione;

public abstract class Payload implements Runnable
{
    protected PayloadStatus status;
    abstract void completePayload();
    public abstract List<ModificaIlluminazione> analyze();
    @Override
    public void run()
    {
        status = PayloadStatus.Running;
        completePayload();
    }
    public PayloadStatus getStatus() { return status; }
}
