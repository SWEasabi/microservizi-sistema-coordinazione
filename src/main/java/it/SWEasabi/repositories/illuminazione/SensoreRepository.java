package it.SWEasabi.repositories.illuminazione;

import org.springframework.data.jpa.repository.JpaRepository;

import it.SWEasabi.modelli.anagrafica.SensoreAnagrafica;

public interface SensoreRepository extends JpaRepository<SensoreAnagrafica,Long>{
	SensoreAnagrafica findById(long id);
}
