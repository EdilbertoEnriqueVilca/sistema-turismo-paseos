package com.turismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tipos_paseo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPaseo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del tipo de paseo es requerido")
    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "nivel_dificultad")
    private String nivelDificultad; // Fácil, Moderado, Difícil, Experto

    @Column(name = "requiere_experiencia")
    private Boolean requiereExperiencia = false;

    @Column(name = "edad_minima")
    private Integer edadMinima;

    @Column(name = "edad_maxima")
    private Integer edadMaxima;

    @Column(name = "condicion_fisica_requerida")
    private String condicionFisicaRequerida;

    @Column(columnDefinition = "TEXT")
    private String equipoNecesario;

    @Column(name = "icono_url")
    private String iconoUrl;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "tipoPaseo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Paquete> paquetes = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
