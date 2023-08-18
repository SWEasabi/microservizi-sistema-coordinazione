package it.SWEasabi.coordinazione;

import it.SWEasabi.core.CoreIlluminazione;
import it.SWEasabi.modelli.payload.PayloadQueue;
import it.SWEasabi.repositories.illuminazione.AreaRepository;
import it.SWEasabi.repositories.illuminazione.LampAnagraficaRepository;
import it.SWEasabi.repositories.illuminazione.MisuratoreRepository;
import it.SWEasabi.repositories.illuminazione.SensoreRepository;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public LampAnagraficaRepository lampAnagraficaRepository() {
        return Mockito.mock(LampAnagraficaRepository.class);
    }

    @Bean
    public MisuratoreRepository misuratoreRepository() {
        return Mockito.mock(MisuratoreRepository.class);
    }

    @Bean
    public SensoreRepository sensoreRepository() {
        return Mockito.mock(SensoreRepository.class);
    }

    @Bean
    public AreaRepository areaRepository() {
        return Mockito.mock(AreaRepository.class);
    }

    @Bean
    public CoreIlluminazione coreIlluminazione() {
        return Mockito.mock(CoreIlluminazione.class);
    }

    @Bean
    public MqttClient mqttClient() {
        return Mockito.mock(MqttClient.class);
    }

    @Bean
    public PayloadQueue payloadQueue() {
        return new PayloadQueue();
    }

    @Bean
    public Producer producer(PayloadQueue payloadQueue, CoreIlluminazione core) {
        return new Producer(payloadQueue, core);
    }
}
