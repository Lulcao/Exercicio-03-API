package Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private Set<Respostas> respostas;

    @OneToMany(mappedBy = "usuarioCriacao")
    private Set<Questionario> questionariosCriados;
}