package io.github.maxswellyoo.msclientes.application;

import io.github.maxswellyoo.msclientes.application.representation.ClienteRequest;
import io.github.maxswellyoo.msclientes.domain.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@AllArgsConstructor
@Slf4j
public class ClienteController {
    private final ClienteService clienteService;
    @GetMapping
    public ResponseEntity<String> status() {
        log.info("Obtento o status do ms-clientes");
        return ResponseEntity.ok("ok");
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteRequest request) {
        var cliente = request.toModel();
        clienteService.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }
    @GetMapping(params = "cpf")
    public ResponseEntity<?> getDataCliente(@RequestParam("cpf") String cpf) {
        var cliente = clienteService.getByCpf(cpf);
        if(cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }
}
