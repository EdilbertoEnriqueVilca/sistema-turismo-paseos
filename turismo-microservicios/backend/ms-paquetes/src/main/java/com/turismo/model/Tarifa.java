package com.turismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El paquete es requerido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paquete_id", nullable = false)
    private Paquete paquete;

    @NotNull(message = "La fecha de inicio es requerida")
    @Column(nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es requerida")
    @Column(nullable = false)
    private LocalDate fechaFin;

    @NotNull(message = "El precio adulto es requerido")
    @Positive(message = "El precio adulto debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioAdulto;

    @Positive(message = "El precio niño debe ser mayor a 0")
    @Column(precision = 10, scale = 2)
    private BigDecimal precioNino;

    @Positive(message = "El precio senior debe ser mayor a 0")
    @Column(precision = 10, scale = 2)
    private BigDecimal precioSenior;

    @NotNull(message = "El porcentaje de descuento es requerido")
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2) DEFAULT 0")
    private BigDecimal porcentajeDescuento = BigDecimal.ZERO;

    @Column(columnDefinition = "TEXT")
    private String descripcionDescuento;

    @NotNull(message = "El cupo disponible es requerido")
    @Positive(message = "El cupo disponible debe ser mayor a 0")
    @Column(nullable = false)
    private Integer cuposDisponibles;

    @NotNull(message = "El cupo total es requerido")
    @Positive(message = "El cupo total debe ser mayor a 0")
    @Column(nullable = false)
    private Integer cuposTotales;

    @Column(nullable = false)
    private Boolean activo = true;

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

    public Integer getCuposReservados() {
        return cuposTotales - cuposDisponibles;
    }

    public BigDecimal getOcupacion() {
        if (cuposTotales == 0) return BigDecimal.ZERO;
        return BigDecimal.valueOf(getCuposReservados())
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(cuposTotales), 2, java.math.RoundingMode.HALF_UP);
    }
}
