package com.turismo.repository;

import com.turismo.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {

    /**
     * Buscar destino por nombre
     */
    Optional<Destino> findByNombre(String nombre);

    /**
     * Buscar destinos por país
     */
    List<Destino> findByPais(String pais);

    /**
     * Buscar destinos por país y estado activo
     */
    List<Destino> findByPaisAndActivo(String pais, Boolean activo);

    /**
     * Buscar destinos por ciudad
     */
    List<Destino> findByCiudad(String ciudad);

    /**
     * Buscar destinos por ciudad y estado activo
     */
    List<Destino> findByCiudadAndActivo(String ciudad, Boolean activo);

    /**
     * Buscar destinos por país y ciudad
     */
    List<Destino> findByPaisAndCiudad(String pais, String ciudad);

    /**
     * Buscar destinos por país, ciudad y estado activo
     */
    List<Destino> findByPaisAndCiudadAndActivo(String pais, String ciudad, Boolean activo);

    /**
     * Buscar destinos activos
     */
    List<Destino> findByActivo(Boolean activo);

    /**
     * Buscar destinos por nombre (búsqueda parcial)
     */
    List<Destino> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Buscar destinos activos por nombre (búsqueda parcial)
     */
    List<Destino> findByNombreContainingIgnoreCaseAndActivo(String nombre, Boolean activo);

    /**
     * Buscar destinos por descripción (búsqueda parcial)
     */
    List<Destino> findByDescripcionContainingIgnoreCase(String descripcion);

    /**
     * Contar destinos por país
     */
    Long countByPais(String pais);

    /**
     * Contar destinos activos por país
     */
    Long countByPaisAndActivo(String pais, Boolean activo);

    /**
     * Verificar si existe un destino por nombre
     */
    boolean existsByNombre(String nombre);

    /**
     * Obtener todos los países con destinos
     */
    @Query("SELECT DISTINCT d.pais FROM Destino d WHERE d.activo = true ORDER BY d.pais")
    List<String> findAllPaisesActivos();

    /**
     * Obtener todas las ciudades por país
     */
    @Query("SELECT DISTINCT d.ciudad FROM Destino d WHERE d.pais = :pais AND d.activo = true ORDER BY d.ciudad")
    List<String> findCiudadesByPaisActivos(@Param("pais") String pais);
}
