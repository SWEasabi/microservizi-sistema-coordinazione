package it.SWEasabi.modelli.anagrafica;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AreaAnagraficaTest {

    AreaAnagrafica area;
    AreaAnagrafica area_auto;

    @Before
    public void setUp() {
        area = new AreaAnagrafica();
        area_auto = new AreaAnagrafica(1, "area_auto", true, 0, 100);
    }

    @Test
    public void testGetId() {
        assertEquals(0, area.getId());
        assertEquals(1, area_auto.getId());
    }

    @Test
    public void testGetNome() {
        assertEquals("", area.getNome());
        assertEquals("area_auto", area_auto.getNome());
    }

    @Test
    public void testGetModAutomatica() {
        assertFalse(area.getModAutomatica());
        assertTrue(area_auto.getModAutomatica());
    }

    @Test
    public void testGetlvlinf() {
        assertEquals(0, area.getLvlInf());
        assertEquals(0, area_auto.getLvlInf());
    }

    @Test
    public void testGetlvlsup() {
        assertEquals(0, area.getLvlSup());
        assertEquals(100, area_auto.getLvlSup());
    }
}