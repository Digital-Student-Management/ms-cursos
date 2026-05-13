package sistemaEscolar.ms_cursos.models.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO de entrada para crear o actualizar una Sala.
 */
@Data
public class SalaRequestDTO {

    @NotNull(message = "El número de sala es obligatorio")
    @Min(value = 1, message = "El número de sala debe ser mayor a 0")
    private Integer numeroSala;

    @NotNull(message = "La capacidad máxima es obligatoria")
    @Min(value = 1, message = "La capacidad máxima debe ser al menos 1")
    private Integer capacidadMaxima;
}
