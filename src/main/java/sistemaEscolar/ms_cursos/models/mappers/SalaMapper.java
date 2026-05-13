package sistemaEscolar.ms_cursos.models.mappers;

import sistemaEscolar.ms_cursos.models.dto.request.SalaRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.SalaResponseDTO;
import sistemaEscolar.ms_cursos.models.entities.Sala;

/**
 * Mapper estático para la entidad Sala.
 */
public class SalaMapper {

    private SalaMapper() {}

    public static Sala toEntity(SalaRequestDTO dto) {
        Sala sala = new Sala();
        sala.setNumeroSala(dto.getNumeroSala());
        sala.setCapacidadMaxima(dto.getCapacidadMaxima());
        return sala;
    }

    public static SalaResponseDTO toResponseDTO(Sala sala) {
        SalaResponseDTO dto = new SalaResponseDTO();
        dto.setIdSala(sala.getIdSala());
        dto.setNumeroSala(sala.getNumeroSala());
        dto.setCapacidadMaxima(sala.getCapacidadMaxima());
        return dto;
    }

    public static void updateEntityFromDTO(SalaRequestDTO dto, Sala sala) {
        sala.setNumeroSala(dto.getNumeroSala());
        sala.setCapacidadMaxima(dto.getCapacidadMaxima());
    }
}
