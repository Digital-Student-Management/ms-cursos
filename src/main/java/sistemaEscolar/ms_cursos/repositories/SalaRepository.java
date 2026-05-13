package sistemaEscolar.ms_cursos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaEscolar.ms_cursos.models.entities.Sala;

/**
 * Repositorio JPA para la entidad Sala.
 */
@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    /** Verifica si ya existe una sala con el mismo número (para evitar duplicados). */
    boolean existsByNumeroSala(Integer numeroSala);

    /** Igual que el anterior pero excluyendo un ID específico (para updates). */
    boolean existsByNumeroSalaAndIdSalaNot(Integer numeroSala, Long idSala);
}
