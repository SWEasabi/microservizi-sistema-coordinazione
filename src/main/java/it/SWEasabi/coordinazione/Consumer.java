package it.SWEasabi.coordinazione;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

import it.SWEasabi.core.CoreIlluminazione;
import it.SWEasabi.modelli.illuminazione.ModificaIlluminazione;
import it.SWEasabi.modelli.payload.Payload;
import it.SWEasabi.modelli.payload.PayloadQueue;
import it.SWEasabi.modelli.payload.PayloadThread;

public class Consumer implements Runnable
{
    CoreIlluminazione coreIlluminazione;
    private final PayloadQueue payloadQueue;
    private boolean stop = false;
    private MqttClient client;
    private final String topicLampioni, topicLogging;
    public Consumer(PayloadQueue _payloadQueue)
    {
        payloadQueue = _payloadQueue;
        topicLampioni = "lampioni_";
        topicLogging = "logging";
    }
    public void setMqttClient(MqttClient _client)
    {
        client = _client;
    }
    public void setCore(CoreIlluminazione core) {
    	this.coreIlluminazione=core;
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
                List<ModificaIlluminazione> modifiche = payload.analyze(); // modifiche di luminosità ai lampioni
                // scrivo nel db dell'illuminazione e logging i nuovi valori
                for(ModificaIlluminazione mod : modifiche)
                {
                    coreIlluminazione.setIlluminazione(mod.getId(), mod.getLuminosita());

                    // mqtt
                    try
                    {
                        // mando al lampione
                        MqttMessage lampResponse = new MqttMessage();
                        String str1 = "{'valore':" + mod.getLuminosita() + "}";
                        lampResponse.setPayload(str1.getBytes(StandardCharsets.UTF_8));
                        client.publish(topicLampioni + mod.getId(), lampResponse);

                        // mando al sistema di logging
                        MqttMessage loggingResponse = new MqttMessage();
                        String str2 = "{'id':" + mod.getId() + ", valore':" + mod.getLuminosita() + ", 'tipo': 'lampione'}}";
                        loggingResponse.setPayload(str2.getBytes(StandardCharsets.UTF_8));
                        client.publish(topicLogging, loggingResponse);

                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
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
