package cl.ufro.dci.dcipayapi.persistenceTests;

import cl.ufro.dci.dcipayapi.domain.Cuenta;
import cl.ufro.dci.dcipayapi.domain.Titular;
import cl.ufro.dci.dcipayapi.repository.CuentaRepository;
import cl.ufro.dci.dcipayapi.repository.TitularRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ExtendWith({SpringExtension.class})
public class TitularPersistenceTest {
    @Autowired
    CuentaRepository cuentaRepo;
    @Autowired
    TitularRepository titularRepo;

    Cuenta cuenta=new Cuenta();
    Titular titular=new Titular();

    @BeforeEach
    void setUp() throws IOException {
        //Cuenta de ID 1
        cuenta.setCueSaldo(1400);
        cuenta.setTransacciones(new ArrayList<>());
        cuenta.setTitular(titular);

        //Titular de ID 1
        titular.setTitDispositivo("Motorola C-512");
        titular.setTitRut("19971496");
        titular.setTitNombre("Alex");
        titular.setTitClave("Iguana13");
        titular.setTitCorreo("a.sarabia02@ufromail.cl");
        titular.setTitApellidos("Sarabia Toledo");
        titular.setTitDireccion("Urrutia 1013");
        titular.setCuenta(cuenta);

        titularRepo.save(titular);
        cuentaRepo.save(cuenta);

    }
    @AfterEach
    void tearDown() {
        cuentaRepo.deleteAll();
        titularRepo.deleteAll();
    }

    @Test
    @DisplayName("Prueba para buscar un Titular existente en BD por su ID")
    public void buscarTitularPorIdTest(){
        try {
            Titular titularObtenido=titularRepo.findById(1L).get();
            assertEquals("19971496", titularObtenido.getTitRut());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para buscar un Titular inexistente en BD")
    public void titularInexistenteTest(){
        if(titularRepo.findById(999L).isEmpty()){
            Assertions.assertNull(null, "NO EXISTE EL TITULAR");
        }else{
            fail();
        }
    }

    @Test
    @DisplayName("Prueba para verificar el ingreso de un Titular en BD")
    public void guardarTitularTest(){
        Titular titularTemp = titularRepo.save(titular);

        Assertions.assertNotNull(titularTemp, "Product should be saved");
        Assertions.assertNotNull(titularTemp.getTitId(), "Product should have an id when saved");
        Assertions.assertEquals(titular.getTitRut(), titularTemp.getTitRut());
    }

    @Test
    @DisplayName("Prueba para actualizar un Titular existente en BD")
    public void actualizarTitularTest(){
        try {
            Titular titularParaActualizar=titularRepo.findById(1L).get();
            titularParaActualizar.setTitCorreo("correoactualizado@yahoo.cl");
            titularParaActualizar.setTitDireccion("Pedro Adulto 1544");
            titularRepo.save(titularParaActualizar);

            Assertions.assertEquals("correoactualizado@yahoo.cl", titularRepo.findById(1L).get().getTitCorreo());
            Assertions.assertEquals("Pedro Adulto 1544", titularRepo.findById(1L).get().getTitDireccion());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para eliminar un Titular existente en BD")
    public void eliminarTitularTest(){
        try {
            titularRepo.deleteById(1L);
            Assertions.assertEquals(0, titularRepo.count());
        }catch (Exception e) {

        }

    }
}
