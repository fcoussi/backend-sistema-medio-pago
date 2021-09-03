package cl.ufro.dci.dcipayapi.controller.site;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SiteController {

    @GetMapping
    public String index() {
        return "<h1>Bienvenido a DCI Pay</h1>";
    }
}
