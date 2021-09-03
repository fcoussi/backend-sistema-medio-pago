package cl.ufro.dci.dcipayapi.repository;

import cl.ufro.dci.dcipayapi.domain.Titular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface TitularRepository extends CrudRepository<Titular, Long> {
    Optional<Titular> findBytitCorreo(String titCorreo);

    @Modifying
    @Query("UPDATE Titular " +
            "SET titActivo = TRUE WHERE titCorreo = ?1")
   int enableTitular(String titCorreo);


    @Modifying
    @Query(value="UPDATE Titular  set tit_profesion_oficio = ? where tit_correo =?",nativeQuery = true)
    int updateTitProfesionOficio( String titProfesionOficio, String titCorreo);

    @Modifying
    @Query(value="UPDATE Titular  set tit_direccion = ? where tit_correo =?",nativeQuery = true)
    int updateTitDireccion( String titDireccion, String titCorreo);

    @Modifying
    @Query(value="UPDATE Titular  set tit_Clave = ? where tit_Id =?",nativeQuery = true)
    int updateTitClave(String titClave, String titId);

}
