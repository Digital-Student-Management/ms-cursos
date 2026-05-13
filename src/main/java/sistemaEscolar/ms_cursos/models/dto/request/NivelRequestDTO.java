package sistemaEscolar.ms_cursos.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO de entrada para crear o actualizar un Nivel.
 */
@Data
public class NivelRequestDTO {

    @NotBlank(message = "El nombre del nivel no puede estar vacío")
    private String nombreNivel;
}
