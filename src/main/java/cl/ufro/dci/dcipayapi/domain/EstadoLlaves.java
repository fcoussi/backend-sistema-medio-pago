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
@Table(name="EstadoLlaves")
public class EstadoLlaves implements Serializable {
	
	@Column(name="etl_id", nullable=false)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int etlId;
	
	@Column(name="etl_estado", nullable=true, length=255)
	private String etlEstado;
	
	@OneToMany(targetEntity=Llave.class, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "etl_llaves")
	private List<Llave> llaves;
	
}
