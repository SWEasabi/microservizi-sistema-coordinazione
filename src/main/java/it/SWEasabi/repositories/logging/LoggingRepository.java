package it.SWEasabi.repositories.logging;

import org.springframework.data.jpa.repository.JpaRepository;
import it.SWEasabi.modelli.logging.Log;

public interface LoggingRepository extends JpaRepository<Log, Long> {
	
}
