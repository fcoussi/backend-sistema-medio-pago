package cl.ufro.dci.dcipayapi.service.cuentas;

import cl.ufro.dci.dcipayapi.domain.Titular;
import cl.ufro.dci.dcipayapi.repository.TitularRepository;
import cl.ufro.dci.dcipayapi.service.utils.CorreoUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TitularService implements UserDetailsService {

    @Autowired
    TitularRepository titularRepository;
    @Autowired
    CorreoService correoService;

    final private PersonalTokenService personalTokenService;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    final private CorreoUtils correoUtils;

    public boolean validadorCorreo(String correo) {
        return true;
    }

    public String registrarTitular(Titular titular) {
        boolean correoValido = validadorCorreo(titular.getTitCorreo());
        if (!correoValido) {
            throw new IllegalStateException("Correo no valido");
        }
        String token = personalTokenService.registroConfirmacionTitular(
                new Titular(
                        titular.getTitNombre(),
                        titular.getTitApellidos(),
                        titular.getTitCorreo(),
                        titular.getTitClave(),
                        titular.getTitDireccion(),
                        titular.getTitProfesionOficio(),
                        titular.getTitRut(),
                        titular.getTitDispositivo(),
                        titular.getCuenta(),
                        titular.getLlaves()
                )
        );
        //String link = "http://localhost:8080/confirmarcuenta?token=" + token;
        String link = "https://dcipay-postgres.herokuapp.com/confirmarcuenta?token=" + token;
        correoService.EnviarCorreoVerificacion(titular.getTitCorreo(),correoUtils.PlantillaCorreoRegistro(titular.getTitNombre(),titular.getTitApellidos(),link));
        return token;
    }

    public String editarTitular(Titular titular) {
        if(titular.getTitDireccion()!=null && titular.getTitProfesionOficio()!=null){
            titularRepository.updateTitDireccion(titular.getTitDireccion(),titular.getTitCorreo());
            titularRepository.updateTitProfesionOficio(titular.getTitProfesionOficio(),titular.getTitCorreo());
        }else if(titular.getTitDireccion()!=null ){
            titularRepository.updateTitDireccion(titular.getTitDireccion(),titular.getTitCorreo());
        }else if(titular.getTitProfesionOficio()!=null ){
            titularRepository.updateTitProfesionOficio(titular.getTitProfesionOficio(),titular.getTitCorreo());
        }else {
            return null;
        }
        return "Informacion actualizada "+titular;
    }
    //TODO:     VALIDAR, SI EL USUARIO ES ACTIVO, SI ES ACTIVO CAMBIAR LA CONTRASE;A ENCRIPTANDOLA

    public Optional<Titular> solicitarCalve(Titular titular){
        boolean correoExiste = titularRepository.findBytitCorreo(titular.getTitCorreo())
                .isPresent();
        if (correoExiste) {
            String token = personalTokenService.recuperacionClave(obtenerTitular(titular));
            //String link = "http://localhost:8080/recuperarclave?token=" + token;
            String link = "https://dcipay-postgres.herokuapp.com/recuperarclave?token=" + token;
            correoService.EnviarCorreoRecuperacion(titular.getTitCorreo(),correoUtils.PlantillaCorreoRecuperacion(link));

        }
        return null;
    }


    public Iterable<Titular> listarTitulares() {
        return titularRepository.findAll();
    }

    public boolean loginTitular(Titular titular) {
        Optional<Titular> titularRegistrado = titularRepository.findBytitCorreo(titular.getTitCorreo());
        if(titularRegistrado!=null) {
            if(bCryptPasswordEncoder.matches(titular.getTitClave(), titularRegistrado.get().getTitClave())){
                if(titularRegistrado.get().isEnabled()==true){
                    return true;
                }else {
                    throw new IllegalStateException("clave incorrecta");
                }
            } else {
                throw new IllegalStateException("Error");
            }
        }
        return false;
    }

    public Optional<Titular> obtenerTitular(Titular email) {
        Optional<Titular> titularRegistrado = titularRepository.findBytitCorreo(email.getTitCorreo());

        return titularRegistrado;
    }

    public String cambioClave(String token, Titular titular){
        String titiD = personalTokenService.confirmarTokenRecuperarClave(token);
        titular.setTitClave(bCryptPasswordEncoder.encode(titular.getTitClave()));
        titularRepository.updateTitClave(titular.getTitClave(),titiD);

        return "clave cambiada";
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return titularRepository.findBytitCorreo(correo).orElseThrow(() -> new UsernameNotFoundException("No se a encontrado Titular con el email: " + correo));
    }
}
