package cl.ufro.dci.dcipayapi.service.cuentas;

import cl.ufro.dci.dcipayapi.repository.TitularRepository;
import cl.ufro.dci.dcipayapi.service.utils.CorreoUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TitularServiceTest {
    @Autowired
    private TitularRepository titularRepository;
    private CorreoUtils correoUtils;

    @Test
    void validadorCorreo() {
    }

    @Test
    void registrarTitular() {
    }

    @Test
    void editarTitular() {
    }

    @Test
    void solicitarCalve() {
    }

    @Test
    void listarTitulares() {
    }

    @Test
    void loginTitular() {
    }

    @Test
    void cambioClave() {
    }
}