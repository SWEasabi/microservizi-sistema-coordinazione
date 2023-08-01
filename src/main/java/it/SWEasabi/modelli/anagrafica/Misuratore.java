package it.SWEasabi.modelli.anagrafica;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="misuratore")
public class Misuratore {

	@Id
	//@SequenceGenerator(name = "measurerGenerator", initialValue = 13)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long idarea;
	private String tipo;
	private double latitudine;
	private double longitudine;
	
	@OneToOne(mappedBy = "misuratore", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private LampAnagrafica lampione;

	public void setId(long id) {
		this.id = id;
	}

	public LampAnagrafica getLampione() {
		return lampione;
	}

	public void setLampione(LampAnagrafica lampione) {
		this.lampione = lampione;
	}

	public Misuratore()
	{
		this.idarea=0;
		this.tipo="";
		this.latitudine=0.0;
		this.longitudine=0.0;
	}
	
	public Misuratore(long idarea, String tipo, double latitudine, double longitudine) {
		this.idarea = idarea;
		this.tipo = tipo;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
	}
	public long getId() {
		return id;
	}
	public long getIdArea() {
		return idarea;
	}
	public String getTipo() {
		return tipo;
	}
	public double getLatitudine() {
		return latitudine;
	}
	public double getLongitudine() {
		return longitudine;
	}
	public void setIdArea(long idArea) {
		this.idarea = idArea;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}
	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
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

        final Misuratore other = (Misuratore) obj;
        if ((this.getId() == other.getId() && this.getIdArea() == other.getIdArea() && this.getTipo() == other.getTipo() && this.getLatitudine() == other.getLatitudine() && this.getLongitudine() == other.getLongitudine())) {
            return true;
        }

        return false;
	}
}

