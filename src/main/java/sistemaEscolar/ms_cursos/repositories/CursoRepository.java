package sistemaEscolar.ms_cursos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaEscolar.ms_cursos.models.entities.Curso;
import sistemaEscolar.ms_cursos.models.entities.Nivel;
import sistemaEscolar.ms_cursos.models.entities.Sala;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Curso.
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    /** Retorna todos los cursos de un año académico específico. */
    List<Curso> findByAnoAcademicoCurso(Integer anoAcademicoCurso);

    /** Retorna todos los cursos de un nivel específico. */
    List<Curso> findByNivel(Nivel nivel);

    /** Retorna todos los cursos asignados a un docente jefe. */
    List<Curso> findByIdDocenteJefe(Long idDocenteJefe);

    /** Retorna todos los cursos que usan una sala específica. */
    List<Curso> findBySala(Sala sala);

    /**
     * Verifica si ya existe la combinación nivel + letra + año (combinación única).
     * Se usa en creación para evitar duplicados.
     */
    boolean existsByNivelAndLetraAndAnoAcademicoCurso(Nivel nivel, String letra, Integer anoAcademicoCurso);

    /**
     * Igual que el anterior pero excluyendo un ID específico.
     * Se usa en actualización para no bloquear el propio registro.
     */
    boolean existsByNivelAndLetraAndAnoAcademicoCursoAndIdCursoNot(
            Nivel nivel, String letra, Integer anoAcademicoCurso, Long idCurso);

    /** Busca un curso por su combinación única. */
    Optional<Curso> findByNivelAndLetraAndAnoAcademicoCurso(Nivel nivel, String letra, Integer anoAcademicoCurso);
}
