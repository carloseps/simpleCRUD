package imd.ufrn.br.crud.dto;

import imd.ufrn.br.crud.entity.ProfessorEntity;
import imd.ufrn.br.crud.entity.AlunoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TurmaDTO(
        Long id,

        @NotBlank(message = "O nome da turma é obrigatório")
        String nome,

        @NotBlank(message = "O código da turma é obrigatório")
        String codigo,

        List<AlunoEntity> alunos,

        List<ProfessorEntity> professores,
        @NotNull(message = "O status da turma deve ser informado")
        boolean ativa

) {
}
