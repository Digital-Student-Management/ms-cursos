package sistemaEscolar.ms_cursos.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sistemaEscolar.ms_cursos.models.dto.request.SalaRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.SalaResponseDTO;
import sistemaEscolar.ms_cursos.models.entities.Sala;
import sistemaEscolar.ms_cursos.models.mappers.SalaMapper;
import sistemaEscolar.ms_cursos.repositories.SalaRepository;

import java.util.List;

/**
 * Servicio de lógica de negocio para la entidad Sala.
 */
@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<SalaResponseDTO> findAll() {
        return salaRepository.findAll()
                .stream()
                .map(SalaMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public SalaResponseDTO findById(Long id) {
        return SalaMapper.toResponseDTO(findSalaOrThrow(id));
    }

    @Transactional
    public SalaResponseDTO create(SalaRequestDTO dto) {
        if (salaRepository.existsByNumeroSala(dto.getNumeroSala())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una sala con el número: " + dto.getNumeroSala());
        }
        return SalaMapper.toResponseDTO(salaRepository.save(SalaMapper.toEntity(dto)));
    }

    @Transactional
    public SalaResponseDTO update(Long id, SalaRequestDTO dto) {
        Sala sala = findSalaOrThrow(id);
        if (salaRepository.existsByNumeroSalaAndIdSalaNot(dto.getNumeroSala(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe otra sala con el número: " + dto.getNumeroSala());
        }
        SalaMapper.updateEntityFromDTO(dto, sala);
        return SalaMapper.toResponseDTO(salaRepository.save(sala));
    }

    @Transactional
    public void delete(Long id) {
        findSalaOrThrow(id);
        salaRepository.deleteById(id);
    }

    /** Método de utilidad expuesto para que CursoService pueda resolver una Sala por ID. */
    public Sala findSalaOrThrow(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Sala con ID " + id + " no encontrada"));
    }
}
