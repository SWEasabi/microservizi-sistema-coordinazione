package it.SWEasabi.modelli.illuminazione;

public class ModificaIlluminazione
{
    private long id;
    private int luminosita;
    public ModificaIlluminazione(long _id, int _luminosita)
    {
        id = _id;
        luminosita = _luminosita;
    }
    public long getId() { return id; }
    public int getLuminosita() { return luminosita; }
}
