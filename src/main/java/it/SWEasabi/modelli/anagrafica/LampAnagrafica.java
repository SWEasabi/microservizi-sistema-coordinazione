package it.SWEasabi.modelli.anagrafica;

public class LampAnagrafica
{
    private int id, idArea;
    public LampAnagrafica(int _id, int _idArea)
    {
        id = _id;
        idArea = _idArea;
    }
    public int getId() { return id; }
    public int getIdArea() {return idArea; }
}
