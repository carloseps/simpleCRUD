package imd.ufrn.br.crud.dto;

import imd.ufrn.br.crud.enums.Role;

public record RegisterDTO(String login, String password, Role role) {
}
