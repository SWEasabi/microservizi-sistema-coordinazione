package it.SWEasabi.modelli.anagrafica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "lampione")
public class LampAnagrafica
{
	@Id
	@Column(name="idmisuratore")
	private long id;
	private int voltaggio;
	private int luminosita;
	
	@OneToOne
	@JoinColumn(name="idmisuratore")
	@MapsId
	private Misuratore misuratore;
	
	public Misuratore getMisuratore() {
		return misuratore;
	}

	public void setMisuratore(Misuratore misuratore) {
		this.misuratore = misuratore;
	}

	public LampAnagrafica()
	{
		this.voltaggio=0;
		this.luminosita=0;
	}
	
	public LampAnagrafica(int voltaggio, int luminosita)
	{
		this.voltaggio=voltaggio;
		this.luminosita=luminosita;
	}
	
	public long getId() {
		return id;
	}
	public int getVoltaggio() {
		return voltaggio;
	}
	public void setId(long id) {
		this.id=id;
	}
	public void setVoltaggio(int voltaggio) {
		this.voltaggio = voltaggio;
	}
	
	@Override
	public boolean equals(Object obj)
	{
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final LampAnagrafica other = (LampAnagrafica) obj;
        if (this.id == other.getId() && this.voltaggio == other.getVoltaggio() 
        		&& this.luminosita == other.getLuminosita()
        		&& this.misuratore.equals(other.getMisuratore())) {
        		return true;
        }

        return false;
	}

	public int getLuminosita() {
		return luminosita;
	}

	public void setLuminosita(int luminosita) {
		this.luminosita = luminosita;
	}
}
