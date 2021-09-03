package cl.ufro.dci.dcipayapi.repository;

import cl.ufro.dci.dcipayapi.domain.Cuenta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

    @Modifying
    @Query(value="UPDATE Titular SET cue_Id = ?1 WHERE tit_Id =?1",nativeQuery = true)
    int updateCueID(Long cue_Id);

}
