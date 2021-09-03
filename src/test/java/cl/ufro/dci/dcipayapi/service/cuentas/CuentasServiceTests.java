package cl.ufro.dci.dcipayapi.service.cuentas;

import static org.assertj.core.api.Assertions.*;

import cl.ufro.dci.dcipayapi.domain.Cuenta;
import cl.ufro.dci.dcipayapi.domain.Titular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class CuentasServiceTests {
    @Autowired
    private CuentasService cuentasService;

    @Autowired
    private TitularService titularService;


}
