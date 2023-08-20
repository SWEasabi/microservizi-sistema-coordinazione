package it.SWEasabi.modelli.anagrafica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "lampione")
public class LampAnagrafica
{
	@Id
	@Column(name="misuratore_id")
	private long id;
	private int wattaggio;
	private int luminosita;
	
	@OneToOne
	@JoinColumn(name="misuratore_id")
	private Misuratore misuratore;
	
	public Misuratore getMisuratore() {
		return misuratore;
	}

	public void setMisuratore(Misuratore misuratore) {
		this.misuratore = misuratore;
	}

	public LampAnagrafica()
	{
		this.wattaggio=0;
		this.luminosita=0;
	}
	
	public LampAnagrafica(int voltaggio, int luminosita)
	{
		this.wattaggio=voltaggio;
		this.luminosita=luminosita;
	}
	
	public long getId() {
		return id;
	}
	public int getWattaggio() {
		return wattaggio;
	}
	public void setId(long id) {
		this.id=id;
	}
	public void setWattaggio(int wattaggio) {
		this.wattaggio = wattaggio;
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
        if (this.id == other.getId() && this.wattaggio == other.getWattaggio()
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
