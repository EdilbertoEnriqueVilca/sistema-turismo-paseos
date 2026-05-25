package com.turismo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaqueteDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private Long destinoId;
    private String destinoNombre;
    private Long tipoPaseoId;
    private String tipoPaseoNombre;
    private BigDecimal precio;
    private String moneda;
    private Integer duracionDias;
    private Integer cuposDisponibles;
    private Integer cuposReservados;
    private Integer cuposActuales; // Cupos disponibles = total - reservados
    private Boolean estadoActivo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
