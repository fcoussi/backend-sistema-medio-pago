package cl.ufro.dci.dcipayapi.repository;

import cl.ufro.dci.dcipayapi.domain.EstadoLlaves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EstadoLlaveRepository extends CrudRepository<EstadoLlaves, Integer> {
}
