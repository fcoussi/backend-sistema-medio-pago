package cl.ufro.dci.dcipayapi.service.cuentas;

import cl.ufro.dci.dcipayapi.domain.Titular;
import cl.ufro.dci.dcipayapi.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.ufro.dci.dcipayapi.domain.Cuenta;

@Service
public class CuentasService {

    @Autowired
    CuentaRepository cuentaRepository;

    public void cracionCuenta(Titular titular) {
        Cuenta cuenta = new Cuenta(
                titular,
                0.0,
                null
        );
        guardarCuenta(cuenta);
        cuentaRepository.updateCueID(cuenta.getCueId());
    }

    public void guardarCuenta(Cuenta cuenta){
        cuentaRepository.save(cuenta);
    }
    public void actualizarCueiDTitular(Cuenta cuenta){
        cuentaRepository.save(cuenta);
    }

}