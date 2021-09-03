package cl.ufro.dci.dcipayapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="Cuenta")
@EqualsAndHashCode
@NoArgsConstructor
public class Cuenta implements Serializable {

    @Column(name="cue_Id", nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cueId;

    @OneToOne(targetEntity=Titular.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name="tit_id")
    private Titular titular;

    @Column(name="cue_saldo", nullable=false)
    private double cueSaldo;

    @OneToMany(targetEntity=Transaccion.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cue_transacciones")
    private List<Transaccion> transacciones;

    public Cuenta(Titular titular, double cueSaldo, List<Transaccion> transacciones) {
        this.titular = titular;
        this.cueSaldo = cueSaldo;
        this.transacciones = transacciones;
    }
}
