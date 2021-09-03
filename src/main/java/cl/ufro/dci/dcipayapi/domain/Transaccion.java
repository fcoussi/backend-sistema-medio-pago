/**
 * Licensee: Alejandro Valenzuela(Universidad de La Frontera)
 * License Type: Academic
 */
package cl.ufro.dci.dcipayapi.domain;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Data
@Table(name="Transaccion")
public class Transaccion implements Serializable {

    @Column(name="tra_id", nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long traId;

    @ManyToOne(targetEntity=Cuenta.class, fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="cue_receptora_id")
    private Cuenta cuentaReceptora;

    @ManyToOne(targetEntity=Cuenta.class, fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="cue_emisora_id")
    private Cuenta cuentaEmisora;

    @ManyToOne(targetEntity=EstadoTransaccion.class, fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="ett_id")
    private EstadoTransaccion estadoTransaccion;

    @Column(name="tra_monto", nullable=false)
    private double traMonto;

    @Column(name="tra_fecha", nullable=false)
    private Date traFecha;

    @Column(name="tra_descripcion", nullable=true, length=255)
    private String traDescripcion;

}
