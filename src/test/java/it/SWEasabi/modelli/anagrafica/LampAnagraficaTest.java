package it.SWEasabi.modelli.anagrafica;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LampAnagraficaTest {

    Misuratore misuratore;
    LampAnagrafica lampione;
    LampAnagrafica lampione100;

    @Before
    public void setUp() {
        misuratore = new Misuratore();

        lampione = new LampAnagrafica();
        lampione100 = new LampAnagrafica(100, 100);
        lampione.setMisuratore(misuratore);
    }

    @Test
    public void testGetMisuratore() {
        assertEquals(misuratore, lampione.getMisuratore());
    }

    @Test
    public void testSetMisuratore() {
        Misuratore misuratore1 = new Misuratore();
        misuratore1.setId(1);

        lampione.setMisuratore(misuratore1);
        assertEquals(misuratore1, lampione.getMisuratore());
        lampione.setMisuratore(misuratore);
        assertEquals(misuratore, lampione.getMisuratore());
    }

    @Test
    public void testGetId() {
        assertEquals(0, lampione.getId());
    }

    @Test
    public void testSetId() {
        lampione.setId(1);
        assertEquals(1, lampione.getId());
        lampione.setId(0);
        assertEquals(0, lampione.getId());
    }

    @Test
    public void testGetWattaggio() {
        assertEquals(100, lampione100.getWattaggio());
    }

    @Test
    public void testSetWattaggio() {
        lampione100.setWattaggio(0);
        assertEquals(0, lampione100.getWattaggio());
        lampione100.setWattaggio(100);
        assertEquals(100, lampione100.getWattaggio());
    }

    @Test
    public void testGetLuminosita() {
        assertEquals(100, lampione100.getLuminosita());
    }

    @Test
    public void testSetLuminosita() {
        lampione100.setLuminosita(100);
        assertEquals(100, lampione100.getLuminosita());
        lampione100.setLuminosita(0);
        assertEquals(0, lampione100.getLuminosita());
    }

    @Test
    public void testNullObj() {
        Object obj = null;
        assertFalse(lampione.equals(obj));
    }

    @Test
    public void testObjClass() {
        Object obj = new Misuratore();
        assertFalse(lampione.equals(obj));
    }

    @Test
    public void testNotEquals() {
        assertFalse(lampione.equals(lampione100));
    }

    @Test
    public void testEquals() {
        LampAnagrafica lampione0 = new LampAnagrafica();
        lampione0.setMisuratore(misuratore);

        assertTrue(lampione.equals(lampione0));
    }
}