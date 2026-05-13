package sistemaEscolar.ms_cursos.models.dto.response;

import lombok.Data;

/**
 * DTO de salida para la entidad Sala.
 */
@Data
public class SalaResponseDTO {

    private Long idSala;
    private Integer numeroSala;
    private Integer capacidadMaxima;
}
