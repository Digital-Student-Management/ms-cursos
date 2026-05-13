package sistemaEscolar.ms_cursos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaEscolar.ms_cursos.models.dto.request.NivelRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.NivelResponseDTO;
import sistemaEscolar.ms_cursos.services.NivelService;

import java.util.List;

/**
 * Controlador REST para la gestión de niveles educativos.
 */
@RestController
@RequestMapping("/api/niveles")
@RequiredArgsConstructor
@Tag(name = "Niveles", description = "API de gestión de niveles educativos")
public class NivelController {

    private final NivelService nivelService;

    @GetMapping
    @Operation(summary = "Listar todos los niveles")
    public ResponseEntity<List<NivelResponseDTO>> getAll() {
        return ResponseEntity.ok(nivelService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un nivel por ID")
    public ResponseEntity<NivelResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(nivelService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo nivel")
    public ResponseEntity<NivelResponseDTO> create(@Valid @RequestBody NivelRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nivelService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un nivel existente")
    public ResponseEntity<NivelResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody NivelRequestDTO dto) {
        return ResponseEntity.ok(nivelService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un nivel por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nivelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
