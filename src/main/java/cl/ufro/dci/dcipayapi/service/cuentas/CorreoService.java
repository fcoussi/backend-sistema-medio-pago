package cl.ufro.dci.dcipayapi.service.cuentas;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
@AllArgsConstructor
public class CorreoService {
    @Autowired
    JavaMailSender mailSender;

    public String EnviarCorreoVerificacion(String destinatario, String contenido) {
        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setFrom("ad.tiempo01@gmail.com");
        mensaje.setTo(destinatario);
        mensaje.setSubject("Confirma tu cuenta en DCI-PAY");
        mensaje.setText(contenido);
        mailSender.send(mensaje);
        System.out.println("Correo enviardo");
        return "Correo enviado satisfactoriamene";
    }
    public String EnviarCorreoRecuperacion(String destinatario, String contenido) {
        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setFrom("ad.tiempo01@gmail.com");
        mensaje.setTo(destinatario);
        mensaje.setSubject("Reinicio de clavem de cuenta DCI-PAY");
        mensaje.setText(contenido);
        mailSender.send(mensaje);

        return "Correo enviado satisfactoriamene";
    }

}