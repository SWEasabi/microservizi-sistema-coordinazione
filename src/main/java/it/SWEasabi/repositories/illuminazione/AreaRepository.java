package it.SWEasabi.repositories.illuminazione;


import org.springframework.data.jpa.repository.JpaRepository;
import it.SWEasabi.modelli.anagrafica.AreaAnagrafica;

public interface AreaRepository extends JpaRepository<AreaAnagrafica,Long> {
	AreaAnagrafica findById(long id);
}
