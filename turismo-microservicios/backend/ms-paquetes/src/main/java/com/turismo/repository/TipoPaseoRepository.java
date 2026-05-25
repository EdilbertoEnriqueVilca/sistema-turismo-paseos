package com.turismo.repository;

import com.turismo.model.TipoPaseo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoPaseoRepository extends JpaRepository<TipoPaseo, Long> {

    /**
     * Buscar tipo de paseo por nombre
     */
    Optional<TipoPaseo> findByNombre(String nombre);

    /**
     * Buscar tipos de paseo por dificultad
     */
    List<TipoPaseo> findByDificultad(String dificultad);

    /**
     * Buscar tipos de paseo por dificultad y estado activo
     */
    List<TipoPaseo> findByDificultadAndActivo(String dificultad, Boolean activo);

    /**
     * Buscar tipos de paseo activos
     */
    List<TipoPaseo> findByActivo(Boolean activo);

    /**
     * Buscar tipos de paseo por nombre (búsqueda parcial)
     */
    List<TipoPaseo> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Buscar tipos de paseo activos por nombre (búsqueda parcial)
     */
    List<TipoPaseo> findByNombreContainingIgnoreCaseAndActivo(String nombre, Boolean activo);

    /**
     * Buscar tipos de paseo por descripción (búsqueda parcial)
     */
    List<TipoPaseo> findByDescripcionContainingIgnoreCase(String descripcion);

    /**
     * Contar tipos de paseo por dificultad
     */
    Long countByDificultad(String dificultad);

    /**
     * Contar tipos de paseo activos por dificultad
     */
    Long countByDificultadAndActivo(String dificultad, Boolean activo);

    /**
     * Verificar si existe un tipo de paseo por nombre
     */
    boolean existsByNombre(String nombre);

    /**
     * Obtener todos los niveles de dificultad
     */
    @Query("SELECT DISTINCT t.dificultad FROM TipoPaseo t WHERE t.activo = true ORDER BY t.dificultad")
    List<String> findAllDificultadesActivas();

    /**
     * Contar tipos de paseo activos
     */
    Long countByActivo(Boolean activo);
}
