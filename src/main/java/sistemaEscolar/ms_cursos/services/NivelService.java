package sistemaEscolar.ms_cursos.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sistemaEscolar.ms_cursos.models.dto.request.NivelRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.NivelResponseDTO;
import sistemaEscolar.ms_cursos.models.entities.Nivel;
import sistemaEscolar.ms_cursos.models.mappers.NivelMapper;
import sistemaEscolar.ms_cursos.repositories.NivelRepository;

import java.util.List;

/**
 * Servicio de lógica de negocio para la entidad Nivel.
 */
@Service
@RequiredArgsConstructor
public class NivelService {

    private final NivelRepository nivelRepository;

    @Transactional(readOnly = true)
    public List<NivelResponseDTO> findAll() {
        return nivelRepository.findAll()
                .stream()
                .map(NivelMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public NivelResponseDTO findById(Long id) {
        return NivelMapper.toResponseDTO(findNivelOrThrow(id));
    }

    @Transactional
    public NivelResponseDTO create(NivelRequestDTO dto) {
        if (nivelRepository.existsByNombreNivel(dto.getNombreNivel())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un nivel con el nombre: " + dto.getNombreNivel());
        }
        return NivelMapper.toResponseDTO(nivelRepository.save(NivelMapper.toEntity(dto)));
    }

    @Transactional
    public NivelResponseDTO update(Long id, NivelRequestDTO dto) {
        Nivel nivel = findNivelOrThrow(id);
        if (nivelRepository.existsByNombreNivelAndIdNivelNot(dto.getNombreNivel(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe otro nivel con el nombre: " + dto.getNombreNivel());
        }
        NivelMapper.updateEntityFromDTO(dto, nivel);
        return NivelMapper.toResponseDTO(nivelRepository.save(nivel));
    }

    @Transactional
    public void delete(Long id) {
        findNivelOrThrow(id);
        nivelRepository.deleteById(id);
    }

    /** Método de utilidad expuesto para que CursoService pueda resolver un Nivel por ID. */
    public Nivel findNivelOrThrow(Long id) {
        return nivelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Nivel con ID " + id + " no encontrado"));
    }
}
