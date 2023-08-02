package it.SWEasabi.modelli.anagrafica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sensore")
public class SensoreAnagrafica {

	@Id
	@Column(name="idmisuratore")
	private long id;
	private int raggio;
	
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
	
	public SensoreAnagrafica()
	{
		this.raggio=0;
	}
	
	public SensoreAnagrafica(int raggio)
	{
		this.raggio=raggio;
	}
	
	public long getId() {
		return id;
	}
	public int getRaggio() {
		return raggio;
	}
	public void setId(long id)
	{
		this.id=id;
	}
	public void setRaggio(int raggio) {
		this.raggio = raggio;
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

        final SensoreAnagrafica other = (SensoreAnagrafica) obj;
        if (this.id == other.getId() && this.raggio == other.getRaggio() && this.misuratore.equals(other.getMisuratore())) {
        		return true;
        }

        return false;
	}
}
