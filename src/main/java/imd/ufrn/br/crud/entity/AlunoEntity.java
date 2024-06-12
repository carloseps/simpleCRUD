package imd.ufrn.br.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import imd.ufrn.br.crud.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "alunos")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private int matricula;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String curso;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private boolean ativo;

    @ManyToMany
    @JoinTable(
            name = "aluno_turmas",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    @JsonIgnore
    private List<TurmaEntity> turmas;

    public AlunoEntity(String nome, String cpf, int matricula, Genero genero, String curso, LocalDate dataNascimento, boolean ativo, List<TurmaEntity> turmas) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.genero = genero;
        this.curso = curso;
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
        this.turmas = turmas;
    }
}
