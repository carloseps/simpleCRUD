package imd.ufrn.br.crud.entity;

import imd.ufrn.br.crud.enums.Genero;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
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

    public AlunoEntity(String nome, String cpf, int matricula, Genero genero, String curso, LocalDate dataNascimento, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.genero = genero;
        this.curso = curso;
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
    }
}
