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
@Table(name = "destinos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del destino es requerido")
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotBlank(message = "El país es requerido")
    @Column(nullable = false, length = 50)
    private String pais;

    @Column(length = 50)
    private String region;

    @Column(length = 50)
    private String ciudad;

    @Column(name = "altitud_metros")
    private Integer altitudMetros;

    @Column(name = "clima_promedio")
    private String climaPromedio;

    @Column(name = "mejor_temporada")
    private String mejorTemporada;

    @Column(columnDefinition = "TEXT")
    private String atracciones;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "destino", cascade = CascadeType.REMOVE, orphanRemoval = true)
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
