/**
 * Licensee: Alejandro Valenzuela(Universidad de La Frontera)
 * License Type: Academic
 */
package cl.ufro.dci.dcipayapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
@Table(name="Llave")
public class Llave implements Serializable {

    @Column(name="lla_id", nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long llaId;

    @ManyToOne(targetEntity=Titular.class, fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name="tit_id")
    private Titular titular;

    @ManyToOne(targetEntity=EstadoLlaves.class, fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="etl_id")
    private EstadoLlaves estadoLlave;

    @Column(name="lla_publica", nullable=true, length=255)
    private String llaPublica;

    @Column(name="lla_verificacion", nullable=true, length=255)
    private String llaVerificacion;

}
