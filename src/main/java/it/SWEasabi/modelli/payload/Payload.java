package it.SWEasabi.modelli.payload;

import java.util.ArrayList;
import it.SWEasabi.modelli.illuminazione.LampIlluminazione;

public abstract class Payload implements Runnable
{
    protected PayloadStatus status;
    abstract void completePayload();
    public abstract ArrayList<LampIlluminazione> analyze();
    @Override
    public void run()
    {
        status = PayloadStatus.Running;
        completePayload();
    }
    public PayloadStatus getStatus() { return status; }
}
