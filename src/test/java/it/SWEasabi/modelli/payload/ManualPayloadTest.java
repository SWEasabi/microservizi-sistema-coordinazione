package it.SWEasabi.modelli.payload;

import it.SWEasabi.modelli.illuminazione.ModificaIlluminazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

class ManualPayloadTest {

    private ManualPayload manualPayload;

    @BeforeEach
    void setUp() {
        manualPayload = new ManualPayload(1, 100);
    }

    @Test
    void testGetIdLampione() {
        assertEquals(1, manualPayload.getIdLampione());
    }

    @Test
    void testGetValore() {
        assertEquals(100, manualPayload.getValore());
    }

    @Test
    void testCompletePayload() {
        manualPayload.completePayload();
        assertEquals(PayloadStatus.Completed, manualPayload.getStatus());
    }

    @Test
    void testAnalyze() {
        List<ModificaIlluminazione> modifiche = manualPayload.analyze();
        ModificaIlluminazione modifica = modifiche.get(0);
        assertEquals(1, modifica.getId());
        assertEquals(100, modifica.getLuminosita());
    }
}