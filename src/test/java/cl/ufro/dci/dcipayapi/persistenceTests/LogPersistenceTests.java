package cl.ufro.dci.dcipayapi.persistenceTests;

import cl.ufro.dci.dcipayapi.domain.*;
import cl.ufro.dci.dcipayapi.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith({SpringExtension.class})
@SpringBootTest
public class LogPersistenceTests {
    @Autowired
    LlaveRepository llaveRepo;
    @Autowired
    TitularRepository titularRepo;
    @Autowired
    CuentaRepository cuentaRepo;
    @Autowired
    EstadoLlaveRepository estadoLlaveRepo;
    @Autowired
    LogRepository logRepository;

    EstadoLlaves estadoLlaves=new EstadoLlaves();
    Llave llave=new Llave();
    Cuenta cuenta=new Cuenta();
    Titular titular=new Titular();
    Log log = new Log();

    @BeforeEach
    void setUp() throws IOException, ParseException {
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

        log.setLogDetalle("Llave activada");
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy");
        Date dt_1 = objSDF.parse("14-07-2021");
        log.setLogFecha(dt_1);
        log.setLlave(llave);

        logRepository.save(log);
    }

    @AfterEach
    void tearDown() {
        logRepository.deleteAll();
        llaveRepo.deleteAll();
        cuentaRepo.deleteAll();
        titularRepo.deleteAll();
        estadoLlaveRepo.deleteAll();
    }

    @Test
    @DisplayName("Prueba para buscar un Log existente en BD por su ID")
    public void buscarLogPorIdTest(){
        try {
            Log logObtenido=logRepository.findById(1L).get();
            assertEquals("Llave activada", logObtenido.getLogDetalle());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para buscar un Log inexistente en BD")
    public void logInexistenteTest(){
        if(logRepository.findById(999L).isEmpty()){
            Assertions.assertNull(null, "NO EXISTE EL LOG");
        }else{
            fail();
        }
    }

    @Test
    @DisplayName("Prueba para verificar el ingreso de un Log en la BD")
    public void guardarLogTest(){
        Log logTemp = logRepository.save(log);
        Assertions.assertNotNull(logTemp, "Product should be saved");
        Assertions.assertNotNull(logTemp.getLogId(), "Product should have an id when saved");
        Assertions.assertEquals(log.getLogDetalle(), logTemp.getLogDetalle());
    }

    @Test
    @DisplayName("Prueba para actualizar un Log existente en BD")
    public void actualizarLogTest() {
        try {
            Log logParaActualizar = logRepository.findById(1L).get();
            logParaActualizar.setLogDetalle("Detalle actualizado");

            logRepository.save(logParaActualizar);

            Assertions.assertEquals("Detalle actualizado", logRepository.findById(1L).get().getLogDetalle());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para eliminar un Log existente en BD")
    public void eliminarLogTest(){
        try {
            log.setLogId(1L);
            logRepository.save(log);
            logRepository.deleteById(1L);
            Assertions.assertEquals(0, logRepository.count());
        }catch (Exception e) {

        }
    }

}
