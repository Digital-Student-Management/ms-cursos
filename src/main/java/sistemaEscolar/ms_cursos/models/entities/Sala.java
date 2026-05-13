package sistemaEscolar.ms_cursos.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa una sala de clases física del colegio.
 * Contiene la capacidad máxima de estudiantes que puede albergar.
 * Un curso puede tener asignada una sala (opcional).
 */
@Entity
@Table(name = "salas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSala;

    @Column(nullable = false, unique = true)
    private Integer numeroSala;

    @Column(nullable = false)
    private Integer capacidadMaxima;
}
