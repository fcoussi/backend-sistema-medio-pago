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
@Table(name="Log")

public class Log implements Serializable {

    @Column(name="log_id", nullable=false, length=20)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne(targetEntity=Llave.class, fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="lla_id")
    private Llave llave;

    @Column(name="log_fecha", nullable=true)
    private Date logFecha;

    @Column(name="log_detalle", nullable=true, length=255)
    private String logDetalle;

}
