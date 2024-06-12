package imd.ufrn.br.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import imd.ufrn.br.crud.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professores")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String nome;
    private int matricula;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String departamento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private float salario;

    private boolean ativo;

    @ManyToMany
    @JoinTable(
            name = "professor_turmas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    @JsonIgnore
    private List<TurmaEntity> turmas;

    public ProfessorEntity(String cpf, String nome, int matricula, Genero genero, String departamento, LocalDate dataNascimento, float salario, boolean ativo, List<TurmaEntity> turmas) {
        this.cpf = cpf;
        this.nome = nome;
        this.matricula = matricula;
        this.genero = genero;
        this.departamento = departamento;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.ativo = ativo;
        this.turmas = turmas;
    }
}
