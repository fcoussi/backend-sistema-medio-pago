package cl.ufro.dci.dcipayapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name="titular")
@EqualsAndHashCode
@NoArgsConstructor
public class Titular implements Serializable, UserDetails {

    @Column(name="tit_id", nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long titId;

    @Column(name="tit_nombre", nullable=true, length=255)
    private String titNombre;

    @Column(name="tit_apellidos", nullable=true, length=255)
    private String titApellidos;

    @Column(name="tit_correo", nullable=true, length=255)
    private String titCorreo;

    @Column(name="tit_clave", nullable=true, length=255)
    private String titClave;

    @Column(name="tit_direccion", nullable=true, length=255)
    private String titDireccion;

    @Column(name="tit_profesion_oficio", nullable=true, length=255)
    private String titProfesionOficio;

    @Column(name="tit_rut", nullable=true, length=255)
    private String titRut;

    @Column(name="tit_dispositivo", nullable=true, length=255)
    private String titDispositivo;

    @Column(name="tit_bloqueado")
    private boolean titBloqueado =false;

    @Column(name="tit_activo")
    private boolean titActivo =false;

    @OneToOne(targetEntity=Cuenta.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="cue_id")
    private Cuenta cuenta;

    @OneToMany(targetEntity=Llave.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tit_llaves")
    private List<Llave> llaves;

    public Titular(String titNombre, String titApellidos, String titCorreo, String titClave, String titDireccion, String titProfesionOficio, String titRut, String titDispositivo, Cuenta cuenta, List<Llave> llaves) {
        this.titNombre = titNombre;
        this.titApellidos = titApellidos;
        this.titCorreo = titCorreo;
        this.titClave = titClave;
        this.titDireccion = titDireccion;
        this.titProfesionOficio = titProfesionOficio;
        this.titRut = titRut;
        this.titDispositivo = titDispositivo;
        this.cuenta = cuenta;
        this.llaves = llaves;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       // SimpleGrantedAuthority autoridad = new SimpleGrantedAuthority();
        return null;
    }

    @Override
    public String getPassword() {
        return titClave;
    }

    @Override
    public String getUsername() {
        return titCorreo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !titBloqueado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return titActivo;
    }
}
