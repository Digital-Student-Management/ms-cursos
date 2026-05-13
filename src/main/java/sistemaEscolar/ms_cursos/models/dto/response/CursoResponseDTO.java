package sistemaEscolar.ms_cursos.models.dto.response;

import lombok.Data;

/**
 * DTO de salida para la entidad Curso.
 * Incluye los objetos Nivel y Sala embebidos (no solo sus IDs) para que el cliente
 * tenga toda la información necesaria en una sola llamada.
 */
@Data
public class CursoResponseDTO {

    private Long idCurso;
    private NivelResponseDTO nivel;
    private String letra;
    private Integer anoAcademicoCurso;

    /** Sala asignada. Puede ser null si aún no se ha asignado una sala al curso. */
    private SalaResponseDTO sala;

    private Long idDocenteJefe;

    /** Nombre completo del curso generado dinámicamente (ej: "1ro Medio A 2026"). */
    public String getNombreCompleto() {
        String nombreNivel = (nivel != null) ? nivel.getNombreNivel() : "Sin nivel";
        return nombreNivel + " " + letra + " " + anoAcademicoCurso;
    }
}
