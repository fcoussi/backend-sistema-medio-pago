package cl.ufro.dci.dcipayapi.controller.titular;

import cl.ufro.dci.dcipayapi.domain.Titular;
import cl.ufro.dci.dcipayapi.service.cuentas.PersonalTokenService;
import cl.ufro.dci.dcipayapi.service.cuentas.TitularService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class TitularController {
    @Autowired
    private TitularService titularService;
    @Autowired
    private PersonalTokenService personalTokenService;

    @GetMapping
    public String index(){
        return "<h1>Bienvenido a DCI-PAY Titular</h1>";
    }

    @PostMapping("/registrarcuenta")
    public String registrarTitular(@RequestBody Titular titular) { return titularService.registrarTitular(titular); }

    @RequestMapping("/logintitular")
    public boolean loginTitular(@RequestBody Titular titular){
        return titularService.loginTitular(titular);
    }

    @GetMapping("/listar")
    public Iterable<Titular> listarTitulares() {
        return  titularService.listarTitulares();
    }

    @GetMapping("/confirmarcuenta")
    public String confirmarRegistrarTitular(@RequestParam("token") String token) { return personalTokenService.confirmarTokenRegistro(token); }

    @PutMapping("/editarcuenta")
    public String editarTitular(@RequestBody Titular titular) {
        return titularService.editarTitular(titular);
    }


    @GetMapping("/recuperarclave")
    public String recuperarClave(@RequestParam("token") String token, @RequestBody Titular titular) {
        titularService.cambioClave(token, titular);
        return "clve guardada";
       // return personalTokenService.confirmarTokenRecuperarClave(email);
    }

    @GetMapping("/solicitarRecuperarclave")
    public Optional<Titular> recuperarClave(@RequestBody Titular titular) {
        System.out.println("sdasdasdasdasdasdasdasd");
        return titularService.solicitarCalve(titular); }

    @GetMapping("/obtenertitular")
    public Optional<Titular> obtenerTitular(@RequestBody Titular titular) { return titularService.obtenerTitular(titular); }
}
