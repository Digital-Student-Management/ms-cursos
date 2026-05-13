package sistemaEscolar.ms_cursos.models.mappers;

import sistemaEscolar.ms_cursos.models.dto.request.NivelRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.NivelResponseDTO;
import sistemaEscolar.ms_cursos.models.entities.Nivel;

/**
 * Mapper estático para la entidad Nivel.
 */
public class NivelMapper {

    private NivelMapper() {}

    public static Nivel toEntity(NivelRequestDTO dto) {
        Nivel nivel = new Nivel();
        nivel.setNombreNivel(dto.getNombreNivel());
        return nivel;
    }

    public static NivelResponseDTO toResponseDTO(Nivel nivel) {
        NivelResponseDTO dto = new NivelResponseDTO();
        dto.setIdNivel(nivel.getIdNivel());
        dto.setNombreNivel(nivel.getNombreNivel());
        return dto;
    }

    public static void updateEntityFromDTO(NivelRequestDTO dto, Nivel nivel) {
        nivel.setNombreNivel(dto.getNombreNivel());
    }
}
