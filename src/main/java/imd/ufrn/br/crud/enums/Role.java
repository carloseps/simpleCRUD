package imd.ufrn.br.crud.enums;

public enum Role {

    USER("User"),
    ADMIN("Admin");

    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
