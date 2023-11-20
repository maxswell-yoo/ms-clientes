package io.github.maxswellyoo.msclientes.application.representation;

import io.github.maxswellyoo.msclientes.domain.entities.Cliente;

public record ClienteRequest(
        String cpf,
        String nome,
        Integer idade

) {
    public Cliente toModel() {
        return new Cliente(cpf, nome, idade);
    }
}
