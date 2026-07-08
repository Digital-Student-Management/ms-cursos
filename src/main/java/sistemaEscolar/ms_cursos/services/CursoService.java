package sistemaEscolar.ms_cursos.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import sistemaEscolar.ms_cursos.models.dto.request.CursoRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.CursoResponseDTO;
import sistemaEscolar.ms_cursos.models.entities.Curso;
import sistemaEscolar.ms_cursos.models.entities.Nivel;
import sistemaEscolar.ms_cursos.models.entities.Sala;
import sistemaEscolar.ms_cursos.models.mappers.CursoMapper;
import sistemaEscolar.ms_cursos.repositories.CursoRepository;

import java.util.List;

/**
 * Servicio de lógica de negocio para la entidad Curso.
 * Orquesta la resolución de Nivel y Sala (entidades locales) y la validación
 * del docente jefe (entidad remota en ms-usuarios).
 */
@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final NivelService nivelService;
    private final SalaService salaService;
    private final RestTemplate restTemplate;

    private static final String MS_USUARIOS_URL = "http://localhost:8089/api/usuarios/";

    // ─── Lectura ─────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> findAll() {
        return cursoRepository.findAll().stream()
                .map(CursoMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CursoResponseDTO findById(Long id) {
        return CursoMapper.toResponseDTO(findCursoOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> findByAno(Integer ano) {
        return cursoRepository.findByAnoAcademicoCurso(ano).stream()
                .map(CursoMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> findByNivel(Long idNivel) {
        Nivel nivel = nivelService.findNivelOrThrow(idNivel);
        return cursoRepository.findByNivel(nivel).stream()
                .map(CursoMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> findByDocenteJefe(Long idDocente) {
        return cursoRepository.findByIdDocenteJefe(idDocente).stream()
                .map(CursoMapper::toResponseDTO)
                .toList();
    }

    // ─── Creación ────────────────────────────────────────────────────────────

    @Transactional
    public CursoResponseDTO create(CursoRequestDTO dto) {
        // 1. Resolver entidades locales (Nivel y Sala)
        Nivel nivel = nivelService.findNivelOrThrow(dto.getIdNivel());
        Sala sala = (dto.getIdSala() != null) ? salaService.findSalaOrThrow(dto.getIdSala()) : null;

        // 2. Validar docente en ms-usuarios
        validarDocenteExiste(dto.getIdDocenteJefe());

        // 3. Verificar que no exista el mismo curso (nivel + letra + año)
        validarCursoUnico(nivel, dto.getLetra(), dto.getAnoAcademicoCurso(), null);

        Curso nuevo = CursoMapper.toEntity(dto, nivel, sala);
        return CursoMapper.toResponseDTO(cursoRepository.save(nuevo));
    }

    // ─── Actualización ───────────────────────────────────────────────────────

    @Transactional
    public CursoResponseDTO update(Long id, CursoRequestDTO dto) {
        Curso existente = findCursoOrThrow(id);

        Nivel nivel = nivelService.findNivelOrThrow(dto.getIdNivel());
        Sala sala = (dto.getIdSala() != null) ? salaService.findSalaOrThrow(dto.getIdSala()) : null;

        validarDocenteExiste(dto.getIdDocenteJefe());
        validarCursoUnico(nivel, dto.getLetra(), dto.getAnoAcademicoCurso(), id);

        CursoMapper.updateEntityFromDTO(dto, existente, nivel, sala);
        return CursoMapper.toResponseDTO(cursoRepository.save(existente));
    }

    // ─── Eliminación ─────────────────────────────────────────────────────────

    @Transactional
    public void delete(Long id) {
        findCursoOrThrow(id);
        cursoRepository.deleteById(id);
    }

    // ─── Métodos privados de soporte ─────────────────────────────────────────

    private Curso findCursoOrThrow(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Curso con ID " + id + " no encontrado"));
    }

    private void validarCursoUnico(Nivel nivel, String letra, Integer ano, Long excludeId) {
        boolean existe = (excludeId == null)
                ? cursoRepository.existsByNivelAndLetraAndAnoAcademicoCurso(nivel, letra, ano)
                : cursoRepository.existsByNivelAndLetraAndAnoAcademicoCursoAndIdCursoNot(nivel, letra, ano, excludeId);

        if (existe) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe el curso " + nivel.getNombreNivel() + " " + letra + " para el año " + ano);
        }
    }

    /**
     * Consulta ms-usuarios para verificar que el docente existe.
     * Lanza 503 si ms-usuarios no está disponible.
     */
    private void validarDocenteExiste(Long idDocente) {
        try {
            restTemplate.getForObject(MS_USUARIOS_URL + idDocente, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "El docente jefe con ID " + idDocente + " no existe en el sistema");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "No se pudo conectar con ms-usuarios para validar el docente: " + e.getMessage());
        }
    }
}
