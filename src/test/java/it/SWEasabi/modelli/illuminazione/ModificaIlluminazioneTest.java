package it.SWEasabi.modelli.illuminazione;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModificaIlluminazioneTest {

    ModificaIlluminazione mod;

    @Before
    public void setUp() throws Exception {
        mod = new ModificaIlluminazione(0, 100);
    }

    @Test
    public void testGetId() {
        assertEquals(0, mod.getId());
    }

    @Test
    public void testGetLuminosita() {
        assertEquals(100, mod.getLuminosita());
    }
}