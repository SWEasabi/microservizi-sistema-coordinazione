package it.SWEasabi.coordinazione;

import java.util.ArrayList;

import it.SWEasabi.modelli.illuminazione.LampIlluminazione;
import it.SWEasabi.modelli.payload.Payload;
import it.SWEasabi.modelli.payload.PayloadQueue;
import it.SWEasabi.modelli.payload.PayloadThread;

public class Consumer implements Runnable
{
    private final PayloadQueue payloadQueue;
    private boolean stop = false;
    public Consumer(PayloadQueue _payloadQueue)
    {
        payloadQueue = _payloadQueue;
    }
    @Override
    public void run()
    {
        while(!stop)
        {
            while (payloadQueue.isEmpty())
            {
                try
                {
                    payloadQueue.waitOnEmpty();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                    break;
                }
            }
            PayloadThread payloadThread = payloadQueue.remove();
            if(payloadThread.isCompleted())
            {
                Payload payload = payloadThread.getPayload();
                // analisi di ciò che è stato letto
                ArrayList<LampIlluminazione> modifiche = payload.analyze(); // modifiche di luminosità ai lampioni
                // scrivo nel db dell'illuminazione i nuovi valori
                // scrivo nel db del logging le modifiche fatte con una chiamata API
                // imposto effettivamente la nuova luminosità con il MqttWriter
                // foreach
            }
            else if(payloadThread.isRunning())
            {
                // il payload non è ancora completo => lo rimetto nella coda
                payloadQueue.add(payloadThread);
            }
            // else il payload è in errore, non faccio nulla
        }
    }
    public void stop()
    {
        stop = true;
    }
}
