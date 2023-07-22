package it.SWEasabi.mqtt;

import it.SWEasabi.modelli.illuminazione.LampIlluminazione;

public interface MqttWriter
{
    void Write(LampIlluminazione lamp); // modifica il valore della luminosità
}
