package cl.ufro.dci.dcipayapi.service.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CorreoUtils {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String PlantillaCorreoRegistro(String nombre, String apellido, String link) {
        return "Hola "+nombre+" "+apellido+",\n" +
                "\n" +
                "Ya falta poco para que puedas empezar a usar tu cuenta. \n" +
                "\n" +
                "Solo necesitas hacer click en el siguiente enlace para validar tu cuenta:.\n" +
                "\n" +
                ""+link+"";
    }
    public String PlantillaCorreoRecuperacion(String link) {
        return "Estimado:"+",\n" +
                "\n" +
                "Se a solicitad la recuperacion de tu clave \n" +
                "\n" +
                "Solo tien:.\n" +
                "\n" +
                ""+link+"";
    }
}
