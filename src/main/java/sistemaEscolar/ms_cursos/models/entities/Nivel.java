package sistemaEscolar.ms_cursos.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un nivel educativo (ej: "1ro Medio", "2do Medio").
 * Es una tabla de referencia utilizada por Curso.
 */
@Entity
@Table(name = "niveles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNivel;

    @Column(nullable = false, unique = true, length = 50)
    private String nombreNivel;
}
