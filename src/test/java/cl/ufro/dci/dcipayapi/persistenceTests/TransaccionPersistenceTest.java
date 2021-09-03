package cl.ufro.dci.dcipayapi.persistenceTests;

import cl.ufro.dci.dcipayapi.domain.*;
import cl.ufro.dci.dcipayapi.repository.CuentaRepository;
import cl.ufro.dci.dcipayapi.repository.EstadoTransaccionRepository;
import cl.ufro.dci.dcipayapi.repository.TitularRepository;
import cl.ufro.dci.dcipayapi.repository.TransaccionRepository;
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

@SpringBootTest
@ExtendWith({SpringExtension.class})
public class TransaccionPersistenceTest {
    @Autowired
    CuentaRepository cuentaRepo;

    @Autowired
    TitularRepository titularRepo;

    @Autowired
    TransaccionRepository transRepo;

    @Autowired
    EstadoTransaccionRepository estadoTransRepo;

    Cuenta cuenta= new Cuenta();
    Cuenta cuenta2= new Cuenta();
    Titular titular=new Titular();
    Titular titular2=new Titular();
    EstadoTransaccion estadoTransaccion=new EstadoTransaccion();

    Transaccion transaccion=new Transaccion();

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

        //Cuenta de ID 2

        cuenta2.setCueSaldo(2200);
        cuenta2.setTransacciones(new ArrayList<>());
        cuenta2.setTitular(titular2);

        //Titular de ID 2

        titular2.setTitDispositivo("Xiaomi Redmi G-19");
        titular2.setTitRut("10570061");
        titular2.setTitNombre("Alejandro");
        titular2.setTitClave("Condorito321");
        titular2.setTitCorreo("a.valenzuela@ufromail.cl");
        titular2.setTitApellidos("Valenzuela Hermosilla");
        titular2.setTitDireccion("los huertos 1001");
        titular2.setCuenta(cuenta2);

        titularRepo.save(titular2);
        cuentaRepo.save(cuenta2);

        estadoTransaccion = new EstadoTransaccion();
        estadoTransaccion.setEttEstado("Pendiente");
        estadoTransaccion.setTransacciones(new ArrayList<>());

        estadoTransRepo.save(estadoTransaccion);


        //Transaccion
        transaccion.setTraMonto(300);
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy");
        Date dt_1 = objSDF.parse("14-07-2021");
        transaccion.setTraFecha(dt_1);
        transaccion.setTraDescripcion("Pago pensión - Perfect Host");
        transaccion.setCuentaEmisora(cuenta);
        transaccion.setCuentaReceptora(cuenta2);
        transaccion.setEstadoTransaccion(estadoTransaccion);

        transRepo.save(transaccion);
    }

    @AfterEach
    void tearDown() {
        transRepo.deleteAll();
        estadoTransRepo.deleteAll();
        cuentaRepo.deleteAll();
        titularRepo.deleteAll();
    }

    @Test
    @DisplayName("Prueba para buscar una Transaccion existente en BD por su ID")
    public void buscarTransaccionPorIdTest() {
        try {
            Transaccion transaccionObtenida = transRepo.findById(1L).get();
            assertEquals(300, transaccionObtenida.getTraMonto());
            assertEquals("Pago pensión - Perfect Host", transaccionObtenida.getTraDescripcion());
        }catch (Exception e) {

        }
    }

    @Test
    @DisplayName("Prueba para buscar una transacción inexistente en BD")
    public void transaccionInexistenteTest(){
        if(transRepo.findById(999L).isEmpty()){
            Assertions.assertNull(null, "NO EXISTE LA TRANSACCION");
        }else{
            fail();
        }
    }

    @Test
    @DisplayName("Prueba para verificar el ingreso de una transaccion en la BD")
    public void guardarTransaccionTest(){
        Transaccion transTemp = transRepo.save(transaccion);
        Assertions.assertNotNull(transTemp, "Product should be saved");
        Assertions.assertNotNull(transTemp.getTraId(), "Product should have an id when saved");
        Assertions.assertEquals(transaccion.getTraDescripcion(), transTemp.getTraDescripcion());
    }

    @Test
    @DisplayName("Prueba para actualizar una transaccion existente en BD")
    public void actualizarTransaccionTest() {
        try {
            Transaccion transaccionParaActualizar = transRepo.findById(1L).get();
            transaccionParaActualizar.setTraDescripcion("Descripcion actualizada");
            transRepo.save(transaccionParaActualizar);
            Assertions.assertEquals("Descripcion actualizada", transRepo.findById(1L).get().getTraDescripcion());
        }catch (Exception e) {

        }

    }

    @Test
    @DisplayName("Prueba para eliminar una transaccion existente en BD")
    public void eliminarTransaccionTest(){
        try {
            transaccion.setTraId(1L);
            transRepo.save(transaccion);
            transRepo.deleteById(1L);
            Assertions.assertEquals(0, transRepo.count());
        }catch (Exception e)  {

        }

    }


}














