package it.SWEasabi.modelli.payload;

import java.util.LinkedList;
import java.util.Queue;

public class PayloadQueue
{
    private final Queue<PayloadThread> queue = new LinkedList<>();
    private final Object EMPTY_QUEUE = new Object();
    public void add(PayloadThread payload)
    {
        synchronized(queue)
        {
            queue.add(payload);
        }
    }
    public PayloadThread remove()
    {
        synchronized(queue)
        {
            return queue.poll();
        }
    }
    public boolean isEmpty()
    {
        synchronized(queue)
        {
            return queue.isEmpty();
        } 
    }
    // il consumer aspetta se la coda è vuota
    public void waitOnEmpty() throws InterruptedException
    {
        synchronized (EMPTY_QUEUE)
        {
            EMPTY_QUEUE.wait();
        }
    }
    // il producer sveglia il consumer quando la lista non è vuota
    public void notifyAllForEmpty()
    {
        synchronized (EMPTY_QUEUE)
        {
            EMPTY_QUEUE.notifyAll();
        }
    }
}
