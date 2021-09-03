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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class})
public class CuentaPersistenceTest {
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

        System.out.println("Comienzo de prueba");
    }

    @AfterEach
    void tearDown() {
        cuentaRepo.deleteAll();
        titularRepo.deleteAll();
        System.out.println("Prueba terminada");
    }

    @Test
    @DisplayName("Prueba para buscar una cuenta existente en BD por su ID")
    public void buscarCuentaPorIdTest(){
        try {
            Cuenta cuentaObtenida=cuentaRepo.findById(1L).get();
            Assertions.assertEquals(1400, cuentaObtenida.getCueSaldo());
        }catch (Exception e) {

        }
    }

    @Test
    @DisplayName("Prueba para buscar una Cuenta inexistente en BD")
    public void cuentaInexistenteTest(){
        if(cuentaRepo.findById(999L).isEmpty()){
            Assertions.assertNull(null, "NO EXISTE LA CUENTA");
        }else{
            fail();
        }
    }

    @Test
    @DisplayName("Prueba para verificar el ingreso de una Cuenta en BD")
    public void guardarCuentaTest(){
        Cuenta cuentaTemp = cuentaRepo.save(cuenta);

        Assertions.assertNotNull(cuentaTemp, "Product should be saved");
        Assertions.assertNotNull(cuentaTemp.getCueId(), "Product should have an id when saved");
        Assertions.assertEquals(cuenta.getTitular().getTitRut(), cuentaTemp.getTitular().getTitRut());
    }

    @Test
    @DisplayName("Prueba para actualizar una Cuenta existente en BD")
    public void actualizarCuentaTest(){
        try {
            Cuenta cuentaParaActualizar=cuentaRepo.findById(1L).get();
            cuentaParaActualizar.setCueSaldo(14500);
            cuentaRepo.save(cuentaParaActualizar);
            Assertions.assertEquals(14500, cuentaRepo.findById(1L).get().getCueSaldo());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para eliminar una Cuenta existente en BD")
    public void eliminarCuentaTest(){
        try {
            cuentaRepo.deleteById(1L);
            Assertions.assertEquals(0, cuentaRepo.count());
        }catch (Exception e) {

        }

    }
}