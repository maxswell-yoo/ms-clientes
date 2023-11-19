package io.github.maxswellyoo.msclientes.infra.repository;

import io.github.maxswellyoo.msclientes.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);

}
