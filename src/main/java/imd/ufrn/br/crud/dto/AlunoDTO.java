package imd.ufrn.br.crud.dto;

import imd.ufrn.br.crud.enums.Genero;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record AlunoDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 35, message = "O nome deverá ter no máximo {max} caracteres")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF
        String cpf,

        @Min(value = 2024000, message = "A matrícula deve ser maior que 2024000. Ex: 2024001")
        int matricula,

        @Enumerated(EnumType.STRING)
        Genero genero,

        @NotBlank(message = "O curso é obrigatório")
        String curso,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento,

        @NotNull(message = "O status do aluno deve ser informado")
        boolean ativo
) {
}
