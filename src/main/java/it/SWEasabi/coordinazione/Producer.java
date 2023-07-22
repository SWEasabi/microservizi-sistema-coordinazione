package it.SWEasabi.coordinazione;

import it.SWEasabi.modelli.payload.AutoPayload;
import it.SWEasabi.modelli.payload.ManualPayload;
import it.SWEasabi.modelli.payload.PayloadQueue;
import it.SWEasabi.modelli.payload.PayloadThread;

public class Producer
{
    private final PayloadQueue payloadQueue;
    public Producer(PayloadQueue _payloadQueue)
    {
        payloadQueue = _payloadQueue;
    }
    public void OnSensorMessageReceived(int idSensore, int stato, long timestamp)
    {
        // scrivo sul db di logging il nuovo log con una chiamata API
        PayloadThread payload = new PayloadThread(new AutoPayload(idSensore, idSensore, timestamp));
        payloadQueue.add(payload);
        payloadQueue.notifyAllForEmpty();
    }
    public void OnApiRequest(int idLampione, int valore, long timestamp)
    {
        PayloadThread payload = new PayloadThread(new ManualPayload(idLampione, valore, timestamp));
        payloadQueue.add(payload);
        payloadQueue.notifyAllForEmpty();
    }
}
