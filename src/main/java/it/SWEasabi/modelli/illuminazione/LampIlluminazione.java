package it.SWEasabi.modelli.illuminazione;

public class LampIlluminazione
{
    private int id, luminosita;
    private long istanteUltimaModifica;
    public LampIlluminazione(int _id, int _luminosita, long _istanteUltimaModifica)
    {
        id = _id;
        luminosita = _luminosita;
        istanteUltimaModifica = _istanteUltimaModifica;
    }
    public int getId() { return id; }
    public int getLuminosita() { return luminosita; }
    public long getIstanteUltimaModifica() { return istanteUltimaModifica; }
}
