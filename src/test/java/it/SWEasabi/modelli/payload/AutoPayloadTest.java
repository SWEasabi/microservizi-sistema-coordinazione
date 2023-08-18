package it.SWEasabi.modelli.payload;

import it.SWEasabi.core.IlluminationService;
import it.SWEasabi.core.LocalCoreIlluminazione;
import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;
import it.SWEasabi.modelli.anagrafica.LampAnagrafica;
import it.SWEasabi.modelli.illuminazione.ModificaIlluminazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AutoPayloadTest {

    IlluminationService core;
    AutoPayload autoPayload;

    @BeforeEach
    public void setUp() {
        core = new LocalCoreIlluminazione();
    }

    @Test
    public void testAreaInvalid() {
        autoPayload = new AutoPayload(0, 1, core);
        autoPayload.completePayload();
        assertEquals(PayloadStatus.Error, autoPayload.getStatus());
    }

    @Test
    public void testLampsInAreaInvalid() {
        autoPayload = new AutoPayload(1, 1, core);
        autoPayload.completePayload();
        assertEquals(PayloadStatus.Error, autoPayload.getStatus());
    }

    @Test
    public void testLampsInAreaValid() {
        autoPayload = new AutoPayload(2, 1, core);
        autoPayload.completePayload();
        assertEquals(PayloadStatus.Completed, autoPayload.getStatus());
    }

    @Test
    public void testAnalyzeNoDiff() {
        autoPayload = new AutoPayload(2, 1, core);
        autoPayload.completePayload();
        List<ModificaIlluminazione> expected = new ArrayList<ModificaIlluminazione>();
        assertEquals(expected, autoPayload.analyze());
    }

    @Test
    public void testAnalyze() {
        autoPayload = new AutoPayload(3, 1, core);
        autoPayload.completePayload();
        assertEquals(10, autoPayload.analyze().size());
    }

}