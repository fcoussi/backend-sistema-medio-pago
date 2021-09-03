package cl.ufro.dci.dcipayapi.service.cuentas;

import cl.ufro.dci.dcipayapi.domain.PersonalToken;
import cl.ufro.dci.dcipayapi.domain.Titular;
import cl.ufro.dci.dcipayapi.repository.PersonalTokenRepository;
import cl.ufro.dci.dcipayapi.repository.TitularRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PersonalTokenService {

    @Autowired
    TitularRepository titularRepository;
    @Autowired
    CuentasService cuentasService;


    private final PersonalTokenRepository personalTokenRepository;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registroConfirmacionTitular(Titular titular) {
        boolean correoExiste = titularRepository.findBytitCorreo(titular.getTitCorreo())
                .isPresent();
        if (correoExiste) {
            throw new IllegalStateException("Correo ya existe");
        }
        titular.setTitClave(bCryptPasswordEncoder.encode(titular.getTitClave()));

        cuentasService.cracionCuenta(titular);
        titularRepository.save(titular);

        var token = UUID.randomUUID().toString();
        PersonalToken confirmacionPersonalToken = new PersonalToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20),
                titular
        );
        guardarConfirmacionToken(confirmacionPersonalToken);
        return token;
    }

    public String recuperacionClave(Optional<Titular> titular) {
        var token = UUID.randomUUID().toString();
        PersonalToken confirmacionPersonalToken = new PersonalToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20),
                titular.get()
        );
        guardarConfirmacionToken(confirmacionPersonalToken);
        return token;
    }

    public int setConfirmadoDesde(String token) {
        return personalTokenRepository.updateConfirmadoDesde(
                token, LocalDateTime.now());
    }

    @Transactional
    public String confirmarTokenRegistro(String token) {
        PersonalToken confirmationPersonalToken = getToken(token).orElseThrow(() -> new IllegalStateException("token no encontrado"));

        if (confirmationPersonalToken.getConfirmadoDesde() != null) {
            throw new IllegalStateException("email ya existe");
        }

        LocalDateTime expiredAt = confirmationPersonalToken.getExpiradoDesde();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expirado");
        }

        setConfirmadoDesde(token);
        enableTitular(confirmationPersonalToken.getTitular().getTitCorreo());
        return "Titular confirmado";
    }

    @Transactional
    public String confirmarTokenRecuperarClave(String token) {
        PersonalToken confirmationPersonalToken = getToken(token).orElseThrow(() -> new IllegalStateException("token no encontrado"));

        if (confirmationPersonalToken.getConfirmadoDesde() != null) {
            throw new IllegalStateException("link utilizado");
        }

        LocalDateTime expiredAt = confirmationPersonalToken.getExpiradoDesde();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expirado");
        }

        setConfirmadoDesde(token);
        return confirmationPersonalToken.getTitular().getTitId().toString();
    }

    public int enableTitular(String correo) {
        return titularRepository.enableTitular(correo);
    }

    public void guardarConfirmacionToken(PersonalToken personalToken){
        personalTokenRepository.save(personalToken);
    }

    public Optional<PersonalToken> getToken(String token) {
        return personalTokenRepository.findByToken(token);
    }






}
