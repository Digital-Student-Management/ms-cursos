package sistemaEscolar.ms_cursos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaEscolar.ms_cursos.models.entities.Nivel;

/**
 * Repositorio JPA para la entidad Nivel.
 */
@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {

    /** Verifica si ya existe un nivel con el mismo nombre (para evitar duplicados). */
    boolean existsByNombreNivel(String nombreNivel);

    /** Igual que el anterior pero excluyendo un ID específico (para updates). */
    boolean existsByNombreNivelAndIdNivelNot(String nombreNivel, Long idNivel);
}
