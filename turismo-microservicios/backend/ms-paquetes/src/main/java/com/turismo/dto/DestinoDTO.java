package com.turismo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DestinoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String pais;
    private String ciudad;
    private String atractivos;
    private Boolean activo;
}
