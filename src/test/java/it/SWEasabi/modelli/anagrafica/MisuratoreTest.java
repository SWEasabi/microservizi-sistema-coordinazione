package it.SWEasabi.modelli.anagrafica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class MisuratoreTest {

    Misuratore misuratore;
    SensoreAnagrafica sensore;
    Misuratore misuratore_sensore;
    LampAnagrafica lampione;
    Misuratore misuratore_lampione;

    @BeforeEach
    public void setUp() {
        misuratore = new Misuratore();

        sensore = new SensoreAnagrafica();
        misuratore_sensore = new Misuratore(0, "Sensore", 0.0, 0.0);
        sensore.setMisuratore(misuratore_sensore);
        misuratore_sensore.setSensore(sensore);

        lampione = new LampAnagrafica();
        misuratore_lampione = new Misuratore(0, "Lampione", 0.0, 0.0);
        lampione.setMisuratore(misuratore_lampione);
        misuratore_lampione.setLampione(lampione);

    }

    @Test
    public void testGetSensore() {
        assertEquals(sensore, misuratore_sensore.getSensore());
    }

    @Test
    public void testSetSensore() {
        SensoreAnagrafica sensore1 = new SensoreAnagrafica();
        sensore1.setMisuratore(misuratore_sensore);

        misuratore_sensore.setSensore(sensore1);
        assertEquals(sensore1, misuratore_sensore.getSensore());
        misuratore_sensore.setSensore(sensore);
        assertEquals(sensore, misuratore_sensore.getSensore());
    }

    @Test
    public void testGetLampione() {
        assertEquals(lampione, misuratore_lampione.getLampione());
    }

    @Test
    public void testSetLampione() {
        LampAnagrafica lampione1 = new LampAnagrafica();
        lampione1.setMisuratore(misuratore_lampione);

        misuratore_lampione.setLampione(lampione1);
        assertEquals(lampione1, misuratore_lampione.getLampione());
        misuratore_lampione.setLampione(lampione);
        assertEquals(lampione, misuratore_lampione.getLampione());
    }

    @Test
    public void testGetId() {
        assertEquals(0, misuratore.getId());
    }

    @Test
    public void testSetId() {
        misuratore.setId(1);
        assertEquals(1, misuratore.getId());
        misuratore.setId(0);
        assertEquals(0, misuratore.getId());
    }

    @Test
    public void testGetIdArea() {
        assertEquals(0, misuratore.getIdArea());
    }

    @Test
    public void testSetIdArea() {
        misuratore.setIdArea(1);
        assertEquals(1, misuratore.getIdArea());
        misuratore.setIdArea(0);
        assertEquals(0, misuratore.getIdArea());
    }

    @Test
    public void testGetTipo() {
        assertEquals("Sensore", misuratore_sensore.getTipo());
        assertEquals("Lampione", misuratore_lampione.getTipo());
    }

    @Test
    public void testSetTipo() {
        misuratore_sensore.setTipo("Lampione");
        misuratore_lampione.setTipo("Sensore");
        assertEquals("Sensore", misuratore_lampione.getTipo());
        assertEquals("Lampione",  misuratore_sensore.getTipo());
        misuratore_sensore.setTipo("Sensore");
        misuratore_lampione.setTipo("Lampione");
        assertEquals("Sensore", misuratore_sensore.getTipo());
        assertEquals("Lampione", misuratore_lampione.getTipo());
    }

    @Test
    public void testGetLatitudine() {
        assertEquals(0.0, misuratore.getLatitudine(), 10^-8);
    }

    @Test
    public void testSetLatitudine() {
        misuratore.setLatitudine(0.00000001);
        assertEquals(0.00000001, misuratore.getLatitudine(), 10^-8);
        misuratore.setLatitudine(0.0);
        assertEquals(0.0, misuratore.getLatitudine(), 10^-8);
    }

    @Test
    public void testGetLongitudine() {
        assertEquals(0.0, misuratore.getLongitudine(), 10^-8);
    }

    @Test
    public void testSetLongitudine() {
        misuratore.setLongitudine(0.00000001);
        assertEquals(0.00000001, misuratore.getLongitudine(), 10^-8);
        misuratore.setLongitudine(0.0);
        assertEquals(0.0, misuratore.getLongitudine(), 10^-8);
    }

    @Test
    public void testNullObj() {
        Object obj = null;
        assertFalse(misuratore.equals(obj));
    }

    @Test
    public void testObjClass() {
        Object obj = new SensoreAnagrafica();
        assertFalse(misuratore.equals(obj));
    }

    @Test
    public void testNotEquals() {
        assertFalse(misuratore.equals(misuratore_sensore));
        assertFalse(misuratore.equals(misuratore_lampione));
    }

    @Test
    public void testEquals() {
        Misuratore misuratore1 = new Misuratore();

        assertEquals(misuratore, misuratore1);
    }
}

