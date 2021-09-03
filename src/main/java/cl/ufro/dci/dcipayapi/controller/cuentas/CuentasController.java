package cl.ufro.dci.dcipayapi.controller.cuentas;


import cl.ufro.dci.dcipayapi.domain.Cuenta;
import cl.ufro.dci.dcipayapi.repository.CuentaRepository;
import cl.ufro.dci.dcipayapi.service.cuentas.CuentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentasController {

    @Autowired
    private CuentasService cuentasService;
    private CuentaRepository cuentaRepository;


    @GetMapping
    public String index(){
        return "<h1>CuentasPage</h1>";
    }

//    @PostMapping("")
//    public Cuenta registrarCuenta(@RequestBody Cuenta cuenta){
//        return cuentasService.registrarCuenta(cuenta);
//    }
//
//    @GetMapping("/list")
//    public Iterable<Cuenta> listarCuentas(){
//        return cuentasService.listarCuentas();
//    }
//
//    @GetMapping("/{id}")
//    public Cuenta obtenerCuenta(@PathVariable long id){
//        return cuentasService.obtenerCuenta(id);
//    }
//
//    @PutMapping("/id")
//    public Cuenta editarCuenta(@RequestBody Cuenta cuenta, @PathVariable long id){
//        cuenta.setCueId(id);
//        return cuentasService.editarCuenta(cuenta,id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void borrarCuenta(@PathVariable long id){
//        cuentasService.borrarCuenta(id);
//        return;
//    }


}
