package imd.ufrn.br.crud.enums;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
