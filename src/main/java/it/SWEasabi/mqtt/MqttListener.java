package it.SWEasabi.mqtt;

public interface MqttListener
{
    void Listen(); // ascolta i messaggi, e chiama il Producer.OnSensorMessageReceived
}
