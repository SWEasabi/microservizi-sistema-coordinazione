package it.SWEasabi.repositories.illuminazione;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.SWEasabi.modelli.anagrafica.Misuratore;

public interface MisuratoreRepository extends JpaRepository<Misuratore, Long> {
	List<Misuratore> findByIdareaAndTipo(long idarea,String tipo);
}
