package cl.ufro.dci.dcipayapi.domain;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class PersonalToken {

    @SequenceGenerator(
            name = "secuencia_personal_token",
            sequenceName = "secuencia_personal_token",
            allocationSize = 1
    )
    @Column(name="tok_id",nullable = false)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "secuencia_personal_token"
    )
    private Long id;
    @Column(name="tok_token",nullable = false)
    private String token;
    @Column(name="tok_creadoDesde",nullable = false)
    private LocalDateTime creadoDesde;
    @Column(name="tok_expiradoDesde",nullable = false)
    private LocalDateTime expiradoDesde;
    @Column(name="tok_confirmadoDesde",nullable = true)
    private LocalDateTime confirmadoDesde;
    @ManyToOne
    @JoinColumn(
          nullable = false,
          name =  "tit_id"
    )
    private Titular titular;

    public PersonalToken(String token, LocalDateTime creadoDesde, LocalDateTime expiradoDesde, Titular titular) {
        this.token = token;
        this.creadoDesde = creadoDesde;
        this.expiradoDesde = expiradoDesde;
        this.titular = titular;
    }
}
