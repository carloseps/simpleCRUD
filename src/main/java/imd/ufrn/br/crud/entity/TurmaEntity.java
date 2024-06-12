package imd.ufrn.br.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "turmas")
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigo;

    @ManyToMany(mappedBy = "turmas", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AlunoEntity> alunos;

    @ManyToMany(mappedBy = "turmas", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProfessorEntity> professores;

    private boolean ativa;

    public TurmaEntity(String nome, String codigo, List<AlunoEntity> alunos, List<ProfessorEntity> professores, boolean ativa) {
        this.nome = nome;
        this.codigo = codigo;
        this.alunos = alunos;
        this.professores = professores;
        this.ativa = ativa;
    }
}
