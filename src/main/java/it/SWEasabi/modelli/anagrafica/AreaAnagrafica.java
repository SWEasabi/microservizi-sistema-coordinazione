package it.SWEasabi.modelli.anagrafica;

public class AreaAnagrafica
{
    private int id, lvlInf, lvlSup;
    private String nome;
    private boolean modAutomatica;
    public AreaAnagrafica(int _id, String _nome, boolean _modAutomatica, int _lvlInf, int _lvlSup)
    {
        id = _id;
        nome = _nome;
        modAutomatica = _modAutomatica;
        lvlInf = _lvlInf;
        lvlSup = _lvlSup;
    }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public boolean getModAutomatica() { return modAutomatica; }
    public int getLvlInf() { return lvlInf; }
    public int getLvlSup() { return lvlSup; }
}
