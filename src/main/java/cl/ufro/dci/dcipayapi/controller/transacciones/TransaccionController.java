package cl.ufro.dci.dcipayapi.controller.transacciones;

import cl.ufro.dci.dcipayapi.domain.EstadoTransaccion;
import cl.ufro.dci.dcipayapi.domain.Transaccion;
import cl.ufro.dci.dcipayapi.service.transaccionService.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping(value="/saveTransaccion")
    public boolean crearTransaccion(@RequestBody Transaccion transaccion){
        transaccionService.crearTransaccion(transaccion);
        return true;
    }
    
    @PostMapping(value="/saveRecarga")
    public boolean crearRecarga(@RequestBody Transaccion transaccion){
        transaccionService.crearRecarga(transaccion);
        return true;
    }

    @GetMapping("/getTransaction/{id}")
    public Transaccion obtenerTransaccionId(@PathVariable long id){
        Transaccion transaccion = transaccionService.obtenerTransaccion(id);
        return transaccion;
    }

    @PutMapping("/putTransaccion/{id}")
    public Transaccion actualizarTransaccionId(@PathVariable long id){
        return  transaccionService.actualizarTransaccionAprobada(id);
    }

    @PutMapping("/putTransaccionRechazada/{id}")
    public Transaccion actualizarTransaccionAprov(@PathVariable long id){
        return  transaccionService.actualizarTransaccionRechazada(id);
    }

    @GetMapping("/getAllTransaccion")
    public List<Transaccion> obtenerTransaciones(){
        return transaccionService.listarTransacciones();
    }

    @GetMapping("/getAllTransaccionAprob")
    public List<Transaccion> obtenerTransacionesAprobados(){
        return transaccionService.listarTransaccionesAprobados();
    }

    @GetMapping("/getAllTransaccionPend")
    public List<Transaccion> obtenerTransacionesPendientes(){
        return transaccionService.listarTransaccionesPendiente();
    }

    /* CONTROLADORES PARA ESTADO TRANSACCION */

    @PostMapping(value="/saveEstadoTransaccion")
    public EstadoTransaccion crearEstadoTransaccion(@RequestBody EstadoTransaccion estadoTransaccion){
        return transaccionService.crearEstadoTransaccion(estadoTransaccion);
    }

    @GetMapping("/getAllEstadoTransaccion")
    public List<EstadoTransaccion> obtenerEstadoTransaccion(){
        return transaccionService.listarEstadoTransaccion();
    }

}
