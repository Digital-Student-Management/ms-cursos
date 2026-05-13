package sistemaEscolar.ms_cursos.models.mappers;

import sistemaEscolar.ms_cursos.models.dto.request.CursoRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.CursoResponseDTO;
import sistemaEscolar.ms_cursos.models.entities.Curso;
import sistemaEscolar.ms_cursos.models.entities.Nivel;
import sistemaEscolar.ms_cursos.models.entities.Sala;

/**
 * Mapper manual para la entidad Curso.
 * Requiere las entidades Nivel y Sala ya resueltas desde el servicio,
 * ya que el DTO solo trae sus IDs.
 */
public class CursoMapper {

    private CursoMapper() {}

    /**
     * Convierte un RequestDTO a entidad.
     * @param dto    DTO con los datos del cliente
     * @param nivel  Entidad Nivel ya buscada por ID en el servicio
     * @param sala   Entidad Sala ya buscada por ID (puede ser null si no se asignó)
     */
    public static Curso toEntity(CursoRequestDTO dto, Nivel nivel, Sala sala) {
        Curso curso = new Curso();
        curso.setNivel(nivel);
        curso.setLetra(dto.getLetra());
        curso.setAnoAcademicoCurso(dto.getAnoAcademicoCurso());
        curso.setSala(sala);
        curso.setIdDocenteJefe(dto.getIdDocenteJefe());
        return curso;
    }

    /** Convierte una entidad a ResponseDTO. Nivel y Sala se mapean con sus propios mappers. */
    public static CursoResponseDTO toResponseDTO(Curso curso) {
        CursoResponseDTO dto = new CursoResponseDTO();
        dto.setIdCurso(curso.getIdCurso());
        dto.setNivel(NivelMapper.toResponseDTO(curso.getNivel()));
        dto.setLetra(curso.getLetra());
        dto.setAnoAcademicoCurso(curso.getAnoAcademicoCurso());
        dto.setSala(curso.getSala() != null ? SalaMapper.toResponseDTO(curso.getSala()) : null);
        dto.setIdDocenteJefe(curso.getIdDocenteJefe());
        return dto;
    }

    /** Actualiza una entidad existente con los datos del DTO (para operaciones PUT). */
    public static void updateEntityFromDTO(CursoRequestDTO dto, Curso curso, Nivel nivel, Sala sala) {
        curso.setNivel(nivel);
        curso.setLetra(dto.getLetra());
        curso.setAnoAcademicoCurso(dto.getAnoAcademicoCurso());
        curso.setSala(sala);
        curso.setIdDocenteJefe(dto.getIdDocenteJefe());
    }
}
