package imd.ufrn.br.crud.dto;

import imd.ufrn.br.crud.entity.TurmaEntity;
import imd.ufrn.br.crud.enums.Genero;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record ProfessorDTO(
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 35, message = "O nome deverá ter no máximo {max} caracteres")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF
        String cpf,

        @NotNull(message = "A matrícula é obrigatória")
        @Min(value = 1000, message = "A matrícula deve ser maior que 1000")
        int matricula,

        @Enumerated(EnumType.STRING)
        Genero genero,

        String departamento,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento,

        float salario,

        @NotNull(message = "O status do professor deve ser informado")
        boolean ativo,

        List<TurmaEntity> turmas
) {
}
