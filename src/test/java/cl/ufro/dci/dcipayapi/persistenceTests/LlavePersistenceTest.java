package cl.ufro.dci.dcipayapi.persistenceTests;

import cl.ufro.dci.dcipayapi.domain.*;
import cl.ufro.dci.dcipayapi.repository.CuentaRepository;
import cl.ufro.dci.dcipayapi.repository.EstadoLlaveRepository;
import cl.ufro.dci.dcipayapi.repository.LlaveRepository;
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

@ExtendWith({SpringExtension.class})
@SpringBootTest
public class LlavePersistenceTest {
    @Autowired
    LlaveRepository llaveRepo;
    @Autowired
    TitularRepository titularRepo;
    @Autowired
    CuentaRepository cuentaRepo;
    @Autowired
    EstadoLlaveRepository estadoLlaveRepo;

    EstadoLlaves estadoLlaves=new EstadoLlaves();
    Llave llave=new Llave();
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

        estadoLlaves.setEtlEstado("Desactivada");
        estadoLlaves.setLlaves(new ArrayList<>());
        estadoLlaveRepo.save(estadoLlaves);

        llave.setTitular(titular);
        llave.setEstadoLlave(estadoLlaves);
        llave.setLlaPublica("2HGHJK4");
        llave.setLlaVerificacion("56MNN4347REH8");

        llaveRepo.save(llave);
    }

    @AfterEach
    void tearDown() {
        llaveRepo.deleteAll();
        cuentaRepo.deleteAll();
        titularRepo.deleteAll();
        estadoLlaveRepo.deleteAll();
    }

    @Test
    @DisplayName("Prueba para buscar una Llave existente en BD por su ID")
    public void buscarLlavePorIdTest(){
        try {
            Llave llaveObtenida=llaveRepo.findById(1L).get();
            assertEquals("2HGHJK4", llaveObtenida.getLlaPublica());
            assertEquals("56MNN4347REH8", llaveObtenida.getLlaVerificacion());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para buscar una Llave inexistente en BD")
    public void llaveInexistenteTest(){
        if(llaveRepo.findById(999L).isEmpty()){
            Assertions.assertNull(null, "NO EXISTE LA LLAVE");
        }else{
            fail();
        }
    }

    @Test
    @DisplayName("Prueba para verificar el ingreso de una Llave en la BD")
    public void guardarLlaveTest(){
        Llave llaveTemp = llaveRepo.save(llave);
        Assertions.assertNotNull(llaveTemp, "Product should be saved");
        Assertions.assertNotNull(llaveTemp.getLlaId(), "Product should have an id when saved");
        Assertions.assertEquals(llave.getLlaPublica(), llaveTemp.getLlaPublica());
    }

    @Test
    @DisplayName("Prueba para actualizar una Llave existente en BD")
    public void actualizarLlaveTest(){
        try {
            Llave llaveParaActualizar=llaveRepo.findById(1L).get();
            llaveParaActualizar.setLlaVerificacion("1L651KL6K32GJJH");
            llaveParaActualizar.setLlaPublica("KJFS893RB5");

            llaveRepo.save(llaveParaActualizar);

            Assertions.assertEquals("1L651KL6K32GJJH", llaveRepo.findById(1L).get().getLlaVerificacion());
            Assertions.assertEquals("KJFS893RB5", llaveRepo.findById(1L).get().getLlaPublica());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para eliminar un Llave existente en BD")
    public void eliminarLlaveTest(){
        try {
            llave.setLlaId(1L);
            llaveRepo.save(llave);
            llaveRepo.deleteById(1L);
            Assertions.assertEquals(0, llaveRepo.count());
        }catch (Exception e) {

        }
    }

}
















