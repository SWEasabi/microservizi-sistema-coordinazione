package it.SWEasabi.modelli.anagrafica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "area")
public class AreaAnagrafica
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private int lvlInf, lvlSup;
    private String nome;
    @Column(name="autoMode")
    private boolean modAutomatica;
    public AreaAnagrafica() {
    	id=0;
    	lvlInf=0;
    	lvlSup=0;
    	nome="";
    	modAutomatica=false;
    }
    public AreaAnagrafica(long _id, String _nome, boolean _modAutomatica, int _lvlInf, int _lvlSup)
    {
        id = _id;
        nome = _nome;
        modAutomatica = _modAutomatica;
        lvlInf = _lvlInf;
        lvlSup = _lvlSup;
    }
    public long getId() { return id; }
    public String getNome() { return nome; }
    public boolean getModAutomatica() { return modAutomatica; }
    public int getLvlInf() { return lvlInf; }
    public int getLvlSup() { return lvlSup; }
}
