package it.SWEasabi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.SWEasabi.modelli.anagrafica.LampAnagrafica;

public interface LampAnagraficaRepository extends JpaRepository<LampAnagrafica, Long> {
	LampAnagrafica findById(long id);
}
