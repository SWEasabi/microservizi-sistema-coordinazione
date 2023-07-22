package it.SWEasabi.modelli.payload;

public class PayloadThread
{
    private Payload payload;
    private Thread thread;

    public PayloadThread(Payload _payload)
    {
        payload = _payload;
        thread = new Thread(payload);
        thread.start();
    }
    public boolean isCompleted()
    {
        return payload.getStatus() == PayloadStatus.Completed;
    }
    public boolean isRunning()
    {
        return payload.getStatus() == PayloadStatus.Running;
    }
    public boolean hasError()
    {
        return payload.getStatus() == PayloadStatus.Error;
    }
    public Payload getPayload()
    {
        return payload;
    }
}
