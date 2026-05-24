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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "paquetes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título del paquete es requerido")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "La descripción es requerida")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El destino es requerido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", nullable = false)
    private Destino destino;

    @NotNull(message = "El tipo de paseo es requerido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_paseo_id", nullable = false)
    private TipoPaseo tipoPaseo;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotBlank(message = "La moneda es requerida")
    @Column(nullable = false, length = 3)
    private String moneda; // USD, PEN, EUR, etc.

    @NotNull(message = "La duración en días es requerida")
    @Positive(message = "La duración debe ser mayor a 0")
    @Column(nullable = false)
    private Integer duracionDias;

    @NotNull(message = "Los cupos disponibles son requeridos")
    @Positive(message = "Los cupos disponibles deben ser mayor a 0")
    @Column(nullable = false)
    private Integer cuposDisponibles;

    @NotNull(message = "El cupo total es requerido")
    @Positive(message = "El cupo total debe ser mayor a 0")
    @Column(nullable = false)
    private Integer cuposTotales;

    @Column(columnDefinition = "TEXT")
    private String itinerario;

    @Column(columnDefinition = "TEXT")
    private String incluye;

    @Column(columnDefinition = "TEXT")
    private String noIncluye;

    @Column(columnDefinition = "TEXT")
    private String politicaCancelacion;

    @Column(name = "calificacion_promedio", columnDefinition = "DECIMAL(3,2)")
    private BigDecimal calificacionPromedio;

    @Column(name = "cantidad_resenas")
    private Integer cantidadResenas = 0;

    @Column(name = "imagen_principal_url")
    private String imagenPrincipalUrl;

    @Column(columnDefinition = "TEXT")
    private String galeriaImagenes; // JSON array de URLs

    @NotNull(message = "El estado es requerido")
    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Tarifa> tarifas = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (cuposTotales == null) {
            cuposTotales = cuposDisponibles;
        }
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

    public boolean puedeBuscarse() {
        return activo && cuposDisponibles > 0;
    }
}
