package it.SWEasabi.modelli.payload;

import it.SWEasabi.modelli.illuminazione.ModificaIlluminazione;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.List;

public class PayloadTest {
    private static class TestPayload extends Payload {

        @Override
        void completePayload() {}

        @Override
        public List<ModificaIlluminazione> analyze() {
            return null;
        }
    }

    Payload payload;

    @Before
    public void setUp() throws Exception {
        payload = new TestPayload();
    }

    @Test
    public void testRunAndStatus() {
        payload.run();
        assertEquals(PayloadStatus.Running, payload.getStatus());
    }
}