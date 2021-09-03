/**
 * Licensee: Alejandro Valenzuela(Universidad de La Frontera)
 * License Type: Academic
 */
package cl.ufro.dci.dcipayapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="EstadoTransaccion")
public class EstadoTransaccion implements Serializable {
	
	@Column(name="ett_id", nullable=false)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ettId;
	
	@Column(name="ett_estado", nullable=true, length=255)
	private String ettEstado;
	
	@OneToMany(targetEntity=Transaccion.class, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "ett_transacciones")
	private List<Transaccion> transacciones;
	
}
