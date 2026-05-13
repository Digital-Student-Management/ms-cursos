package sistemaEscolar.ms_cursos.models.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * DTO de entrada para crear o actualizar un Curso.
 * Usa IDs para referenciar Nivel y Sala (que el servicio resolverá a entidades).
 */
@Data
public class CursoRequestDTO {

    @NotNull(message = "El ID del nivel es obligatorio")
    @Positive(message = "El ID del nivel debe ser un número positivo")
    private Long idNivel;

    @NotBlank(message = "La letra no puede estar vacía")
    @Pattern(regexp = "^[A-Z]$", message = "La letra debe ser una sola mayúscula (A-Z)")
    private String letra;

    @NotNull(message = "El año académico es obligatorio")
    @Min(value = 2000, message = "El año académico debe ser mayor a 2000")
    private Integer anoAcademicoCurso;

    /** ID de la sala asignada. Es opcional: un curso puede crearse sin sala asignada aún. */
    @Positive(message = "El ID de sala debe ser un número positivo")
    private Long idSala;

    @NotNull(message = "El ID del docente jefe es obligatorio")
    @Positive(message = "El ID del docente jefe debe ser un número positivo")
    private Long idDocenteJefe;
}
