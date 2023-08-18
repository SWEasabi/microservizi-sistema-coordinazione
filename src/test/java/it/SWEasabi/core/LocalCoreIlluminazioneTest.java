package it.SWEasabi.core;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class LocalCoreIlluminazioneTest {

    private LocalCoreIlluminazione illuminazioneService;

    @BeforeEach
    public void setUp() {
        illuminazioneService = new LocalCoreIlluminazione();
    }

    @Test
    public void testSetIlluminazioneValid() {
        assertTrue(illuminazioneService.setIlluminazione(1000000, 50));
    }

    @Test
    public void testSetIlluminazioneInvalid() {
        assertFalse(illuminazioneService.setIlluminazione(1000000, -10));
    }

    @Test
    public void testGetByIdValid() {
        LampAnagrafica lamp = illuminazioneService.getById(1000000);
        assertNotNull(lamp);
        assertEquals(1000000, lamp.getId());
    }

    @Test
    public void testGetByIdInvalid() {
        assertNull(illuminazioneService.getById(-1000000));
    }

    @Test
    public void testGetLampsInAreaValid() {
        assertTrue(illuminazioneService.getLampsInArea(1000000).size() > 0);
    }

    @Test
    public void testGetLampsInAreaInvalid() {
        assertNull(illuminazioneService.getLampsInArea(-1000000));
    }

    @Test
    public void testGetAreaFromSensorIdValid() {
        assertNotNull(illuminazioneService.getAreaFromSensorId(1000000));
    }

    @Test
    public void testGetAreaFromSensorIdInvalid() {
        assertNull(illuminazioneService.getAreaFromSensorId(-1000000));
    }
}