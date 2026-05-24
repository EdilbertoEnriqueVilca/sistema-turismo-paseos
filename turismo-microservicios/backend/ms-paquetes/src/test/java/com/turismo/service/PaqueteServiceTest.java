package com.turismo.service;

import com.turismo.dto.CreatePaqueteDTO;
import com.turismo.dto.PaqueteDTO;
import com.turismo.model.Paquete;
import com.turismo.repository.PaqueteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PaqueteServiceTest {

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private PaqueteRepository paqueteRepository;

    private CreatePaqueteDTO createPaqueteDTO;

    @BeforeEach
    void setUp() {
        paqueteRepository.deleteAll();

        createPaqueteDTO = new CreatePaqueteDTO();
        createPaqueteDTO.setNombre("Viaje a Machu Picchu");
        createPaqueteDTO.setDescripcion("Visita a la ciudadela inca");
        createPaqueteDTO.setPrecio(BigDecimal.valueOf(1500.00));
        createPaqueteDTO.setDuracionDias(5);
        createPaqueteDTO.setDestino("Cusco");
        createPaqueteDTO.setCategoria("Aventura");
        createPaqueteDTO.setCapacidad(20);
    }

    @Test
    void testCrearPaquete() {
        PaqueteDTO resultado = paqueteService.crear(createPaqueteDTO);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals("Viaje a Machu Picchu", resultado.getNombre());
        assertEquals(20, resultado.getDisponibles());
    }

    @Test
    void testObtenerTodos() {
        paqueteService.crear(createPaqueteDTO);

        List<PaqueteDTO> paquetes = paqueteService.obtenerTodos();

        assertNotNull(paquetes);
        assertEquals(1, paquetes.size());
    }

    @Test
    void testObtenerPorId() {
        PaqueteDTO creado = paqueteService.crear(createPaqueteDTO);

        PaqueteDTO obtenido = paqueteService.obtenerPorId(creado.getId());

        assertNotNull(obtenido);
        assertEquals(creado.getId(), obtenido.getId());
    }
}
