package cl.ufro.dci.dcipayapi.service.transaccionService;

import cl.ufro.dci.dcipayapi.domain.Cuenta;
import cl.ufro.dci.dcipayapi.domain.EstadoTransaccion;
import cl.ufro.dci.dcipayapi.domain.Titular;
import cl.ufro.dci.dcipayapi.domain.Transaccion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class TransaccionServiceTest {

    @Autowired
    TransaccionService service = new TransaccionService();

    @Test
    void crearTransaccion() {
        try{
            Cuenta cuentaEmisora = new Cuenta();
            Cuenta cuentaReceptora = new Cuenta();

            Date fecha = new Date();
            Titular titular = new Titular();
            Titular titular2 = new Titular();

            titular.setTitId(1L);
            //titular.setId(1L);
            titular.setTitNombre("Marcelo");
            //titular.setNombre("Marcelo");
            titular.setTitApellidos("Henriquez");
            titular.setTitCorreo("marcelo@gmail.com");
            //titular.setCorreo("marcelo@gmail.com");
            titular.setTitClave("clave");
            //titular.setContraseña("clave");
            titular.setTitDireccion("Lanco");
            //titular.setDomicilio("Lanco");
            titular.setTitRut("18232323-7");
            //titular.setRut("18232323-7");
            titular.setTitDispositivo("Huawei");
            //titular.setDispositivo("huawei");

            titular2.setTitId(2L);
            titular2.setTitNombre("Sandra");
            titular2.setTitCorreo("sandra@gmail.com");
            titular2.setTitClave("claveS");
            titular2.setTitDireccion("Temuco");
            titular2.setTitRut("19232323-8");
            titular2.setTitDispositivo("i-phone");

            cuentaEmisora.setCueId(1L);
            //cuentaEmisora.setTitId(1L);
            cuentaEmisora.setCueSaldo(50000);
            //cuentaEmisora.setSaldo(1000);
            cuentaEmisora.setTitular(titular);
            //cuentaEmisora.setTitular(titular);

            cuentaReceptora.setCueId(2L);
            //cuentaReceptora.setId(2L);
            cuentaReceptora.setCueSaldo(50000);
            //cuentaReceptora.setSaldo(1000);
            cuentaReceptora.setTitular(titular2);
            //cuentaReceptora.setTitular(titular2);

            Transaccion transaccion = new Transaccion();
            EstadoTransaccion estadoTransaccion = new EstadoTransaccion();
            estadoTransaccion.setEttId(12345);
            estadoTransaccion.setEttEstado("Pendiente");

            transaccion.setTraId(1L);
            //transaccion.setId(1L);
            transaccion.setEstadoTransaccion(estadoTransaccion);
            //transaccion.setEstado(1);
            transaccion.setTraFecha(fecha);
            //transaccion.setFecha(fecha);
            transaccion.setTraMonto(10000);
            //transaccion.setMonto(10000);
            transaccion.setCuentaEmisora(cuentaEmisora);
            //transaccion.setCuentaEmisoraId(cuentaEmisora);
            transaccion.setCuentaReceptora(cuentaReceptora);
            //transaccion.setCuentaReceptoraId(cuentaReceptora);
            service.crearTransaccion(transaccion);
            Transaccion transaccion2 = service.obtenerTransaccion(1L);
            //Transaccion transaccion2 = service.obtenerTransaccion(1L);
            if(transaccion.getTraMonto()==transaccion2.getTraMonto()){
                Assert.isTrue(true);
            }else{
                fail();
            }
        }catch(Exception e){

        }




    }

}