package imd.ufrn.br.crud.entity;

import imd.ufrn.br.crud.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDate;

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
    private Genero genero;
    private String departamento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private float salario;
    private String disciplinaAssociada;

    private boolean ativo;

    public ProfessorEntity(String cpf, String nome, int matricula, Genero genero, String departamento, LocalDate dataNascimento, float salario, String disciplinaAssociada, boolean ativo) {
        this.cpf = cpf;
        this.nome = nome;
        this.matricula = matricula;
        this.genero = genero;
        this.departamento = departamento;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.disciplinaAssociada = disciplinaAssociada;
        this.ativo = ativo;
    }
}
