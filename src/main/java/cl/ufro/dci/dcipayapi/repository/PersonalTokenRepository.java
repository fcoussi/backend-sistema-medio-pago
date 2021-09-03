package cl.ufro.dci.dcipayapi.repository;

import cl.ufro.dci.dcipayapi.domain.PersonalToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Transactional
@Repository
public interface PersonalTokenRepository extends JpaRepository<PersonalToken,Long> {
    Optional<PersonalToken> findByToken(String token);

    @Modifying
    @Query("UPDATE PersonalToken c SET c.confirmadoDesde = ?2 WHERE c.token = ?1")
    int updateConfirmadoDesde(String token, LocalDateTime confirmadoDesde);
}
