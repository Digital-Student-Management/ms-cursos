package sistemaEscolar.ms_cursos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaEscolar.ms_cursos.models.dto.request.SalaRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.SalaResponseDTO;
import sistemaEscolar.ms_cursos.services.SalaService;

import java.util.List;

/**
 * Controlador REST para la gestión de salas de clases.
 */
@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
@Tag(name = "Salas", description = "API de gestión de salas de clases")
public class SalaController {

    private final SalaService salaService;

    @GetMapping
    @Operation(summary = "Listar todas las salas")
    public ResponseEntity<List<SalaResponseDTO>> getAll() {
        return ResponseEntity.ok(salaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sala por ID")
    public ResponseEntity<SalaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sala")
    public ResponseEntity<SalaResponseDTO> create(@Valid @RequestBody SalaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sala existente")
    public ResponseEntity<SalaResponseDTO> update(@PathVariable Long id,
                                                   @Valid @RequestBody SalaRequestDTO dto) {
        return ResponseEntity.ok(salaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sala por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
