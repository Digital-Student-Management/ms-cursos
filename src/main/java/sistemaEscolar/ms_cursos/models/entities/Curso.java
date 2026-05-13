package sistemaEscolar.ms_cursos.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un Curso del colegio.
 * Un curso se identifica por la combinación única de nivel + letra + año (ej: "1ro Medio A 2026").
 *
 * Relaciones internas (misma BD, JPA real):
 *  - nivel: @ManyToOne → tabla niveles
 *  - sala:  @ManyToOne → tabla salas (opcional, puede asignarse después)
 *
 * Relación externa (FK lógica, microservicio diferente):
 *  - idDocenteJefe → ms-usuarios (solo se almacena el ID, sin join JPA)
 */
@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    /** Nivel educativo del curso (ej: "1ro Medio"). FK real a tabla niveles. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_nivel", nullable = false)
    private Nivel nivel;

    /** Letra de la sección (ej: "A", "B"). */
    @Column(nullable = false, length = 5)
    private String letra;

    /** Año académico al que pertenece el curso (ej: 2026). */
    @Column(nullable = false)
    private Integer anoAcademicoCurso;

    /** Sala asignada al curso. Es opcional; puede asignarse después de crear el curso. */
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_sala", nullable = true)
    private Sala sala;

    /**
     * ID del docente jefe (FK lógica a ms-usuarios).
     * No se usa @ManyToOne para mantener el desacoplamiento entre microservicios.
     */
    @Column(nullable = false)
    private Long idDocenteJefe;
}
