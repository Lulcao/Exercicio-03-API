package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questionario")
public class Questionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dataCriacao;
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_usuario_criacao", referencedColumnName = "id")
    private Usuario usuarioCriacao;

    @OneToMany(mappedBy = "questionario")
    private Set<Perguntas> perguntas;

    @OneToMany(mappedBy = "questionario")
    private Set<Respostas> respostas;
}