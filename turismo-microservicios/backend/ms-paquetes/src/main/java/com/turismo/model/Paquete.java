package com.turismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "paquetes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del paquete es requerido")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La descripción es requerida")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "La duración en días es requerida")
    @Positive(message = "La duración debe ser mayor a 0")
    @Column(nullable = false)
    private Integer duracionDias;

    @NotBlank(message = "El destino es requerido")
    @Column(nullable = false, length = 100)
    private String destino;

    @NotBlank(message = "La categoría es requerida")
    @Column(nullable = false, length = 50)
    private String categoria;

    @Positive(message = "La capacidad debe ser mayor a 0")
    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false)
    private Integer disponibles;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

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
