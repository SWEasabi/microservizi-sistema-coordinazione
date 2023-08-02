package it.SWEasabi.modelli.illuminazione;

public class LampIlluminazione
{
    private long id;
    private int luminosita;
    private long istanteUltimaModifica;
    public LampIlluminazione(long _id, int _luminosita, long _istanteUltimaModifica)
    {
        id = _id;
        luminosita = _luminosita;
        istanteUltimaModifica = _istanteUltimaModifica;
    }
    public long getId() { return id; }
    public int getLuminosita() { return luminosita; }
    public long getIstanteUltimaModifica() { return istanteUltimaModifica; }
}
