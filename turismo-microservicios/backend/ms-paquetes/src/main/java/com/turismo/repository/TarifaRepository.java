package com.turismo.repository;

import com.turismo.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    /**
     * Buscar tarifas por paquete
     */
    List<Tarifa> findByPaqueteId(Long paqueteId);

    /**
     * Buscar tarifas activas por paquete
     */
    List<Tarifa> findByPaqueteIdAndActivo(Long paqueteId, Boolean activo);

    /**
     * Buscar tarifas por tipo de tarifa
     */
    List<Tarifa> findByTipoTarifa(String tipoTarifa);

    /**
     * Buscar tarifas activas por tipo de tarifa
     */
    List<Tarifa> findByTipoTarifaAndActivo(String tipoTarifa, Boolean activo);

    /**
     * Buscar tarifas por moneda
     */
    List<Tarifa> findByMoneda(String moneda);

    /**
     * Buscar tarifas activas por moneda
     */
    List<Tarifa> findByMonedaAndActivo(String moneda, Boolean activo);

    /**
     * Buscar tarifas por estado activo
     */
    List<Tarifa> findByActivo(Boolean activo);

    /**
     * Buscar tarifas por paquete y tipo de tarifa
     */
    List<Tarifa> findByPaqueteIdAndTipoTarifa(Long paqueteId, String tipoTarifa);

    /**
     * Buscar tarifas por paquete, tipo de tarifa y estado activo
     */
    List<Tarifa> findByPaqueteIdAndTipoTarifaAndActivo(Long paqueteId, String tipoTarifa, Boolean activo);

    /**
     * Buscar tarifas vigentes (dentro del rango de fechas) por paquete
     */
    @Query("SELECT t FROM Tarifa t WHERE t.paquete.id = :paqueteId " +
           "AND CURRENT_DATE BETWEEN t.fechaVigenciaInicio AND t.fechaVigenciaFin " +
           "AND t.activo = true")
    List<Tarifa> findTarifasVigentesByPaquete(@Param("paqueteId") Long paqueteId);

    /**
     * Buscar tarifas vigentes (dentro del rango de fechas) por paquete y tipo
     */
    @Query("SELECT t FROM Tarifa t WHERE t.paquete.id = :paqueteId " +
           "AND t.tipoTarifa = :tipoTarifa " +
           "AND CURRENT_DATE BETWEEN t.fechaVigenciaInicio AND t.fechaVigenciaFin " +
           "AND t.activo = true")
    Optional<Tarifa> findTarifaVigenteByPaqueteAndTipo(
            @Param("paqueteId") Long paqueteId,
            @Param("tipoTarifa") String tipoTarifa);

    /**
     * Buscar tarifas con descuento
     */
    @Query("SELECT t FROM Tarifa t WHERE t.descuentoPorcentaje > 0 AND t.activo = true")
    List<Tarifa> findTarifasConDescuento();

    /**
     * Buscar tarifas con descuento por paquete
     */
    @Query("SELECT t FROM Tarifa t WHERE t.paquete.id = :paqueteId " +
           "AND t.descuentoPorcentaje > 0 AND t.activo = true")
    List<Tarifa> findTarifasConDescuentoByPaquete(@Param("paqueteId") Long paqueteId);

    /**
     * Contar tarifas por paquete
     */
    Long countByPaqueteId(Long paqueteId);

    /**
     * Contar tarifas activas por paquete
     */
    Long countByPaqueteIdAndActivo(Long paqueteId, Boolean activo);

    /**
     * Contar tarifas por tipo de tarifa
     */
    Long countByTipoTarifa(String tipoTarifa);

    /**
     * Contar tarifas activas
     */
    Long countByActivo(Boolean activo);

    /**
     * Obtener el precio mínimo de tarifas activas por paquete
     */
    @Query("SELECT MIN(t.precio) FROM Tarifa t WHERE t.paquete.id = :paqueteId AND t.activo = true")
    Optional<BigDecimal> findPrecioMinimoByPaquete(@Param("paqueteId") Long paqueteId);

    /**
     * Obtener el precio máximo de tarifas activas por paquete
     */
    @Query("SELECT MAX(t.precio) FROM Tarifa t WHERE t.paquete.id = :paqueteId AND t.activo = true")
    Optional<BigDecimal> findPrecioMaximoByPaquete(@Param("paqueteId") Long paqueteId);

    /**
     * Obtener todos los tipos de tarifa disponibles
     */
    @Query("SELECT DISTINCT t.tipoTarifa FROM Tarifa t WHERE t.activo = true ORDER BY t.tipoTarifa")
    List<String> findAllTiposTarifaActivos();

    /**
     * Obtener todas las monedas disponibles
     */
    @Query("SELECT DISTINCT t.moneda FROM Tarifa t WHERE t.activo = true ORDER BY t.moneda")
    List<String> findAllMonedasActivas();
}
