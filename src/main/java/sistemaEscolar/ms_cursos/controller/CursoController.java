package sistemaEscolar.ms_cursos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaEscolar.ms_cursos.models.dto.request.CursoRequestDTO;
import sistemaEscolar.ms_cursos.models.dto.response.CursoResponseDTO;
import sistemaEscolar.ms_cursos.services.CursoService;

import java.util.List;

/**
 * Controlador REST para la gestión de cursos.
 * Solo delega en el servicio; no contiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "API de gestión de cursos del Colegio Bernardo O'Higgins")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar todos los cursos")
    public ResponseEntity<List<CursoResponseDTO>> getAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por ID")
    public ResponseEntity<CursoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.findById(id));
    }

    @GetMapping("/ano/{ano}")
    @Operation(summary = "Listar cursos por año académico")
    public ResponseEntity<List<CursoResponseDTO>> getByAno(@PathVariable Integer ano) {
        return ResponseEntity.ok(cursoService.findByAno(ano));
    }

    @GetMapping("/nivel/{idNivel}")
    @Operation(summary = "Listar cursos de un nivel específico")
    public ResponseEntity<List<CursoResponseDTO>> getByNivel(@PathVariable Long idNivel) {
        return ResponseEntity.ok(cursoService.findByNivel(idNivel));
    }

    @GetMapping("/docente/{idDocente}")
    @Operation(summary = "Listar cursos asignados a un docente jefe")
    public ResponseEntity<List<CursoResponseDTO>> getByDocenteJefe(@PathVariable Long idDocente) {
        return ResponseEntity.ok(cursoService.findByDocenteJefe(idDocente));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso")
    public ResponseEntity<CursoResponseDTO> create(@Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un curso existente")
    public ResponseEntity<CursoResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.ok(cursoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
