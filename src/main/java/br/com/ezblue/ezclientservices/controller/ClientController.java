package br.com.ezblue.ezclientservices.controller;

import br.com.ezblue.ezclientservices.domain.client.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    @PostMapping
    @Transactional
    @Operation(summary = "Registar Novo Cliente", description = "Método responsável por registrar novos clientes")
    public ResponseEntity<DetailClient> registerClient(@RequestBody @Valid RegisterClient registerClient, UriComponentsBuilder componentsBuilder) {
        return clientServices.register(registerClient,componentsBuilder);
    }

    @GetMapping
    @Operation(summary = "Listar Clientes", description = "Método responsável por listar todos clientes.")
    public ResponseEntity<Page<SimpleClient>> ListClient(@PageableDefault(size = 10, sort = {"firstName", "lastName"}) Pageable pageable) {
        return clientServices.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar cliente", description = "Método responsável exibir os detalhes do cliente.")
    public ResponseEntity<DetailClient> detailClient(@PathVariable UUID id) {
        return clientServices.findById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza dados do Cliente", description = "Método responsável por atualizar os dados do cliente.")
    public ResponseEntity<DetailClient> UpdateClient(@RequestBody @Valid UpdateClient updateGuardian, @PathVariable UUID id) {
        return clientServices.getReferenceById(updateGuardian,id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir clinte", description = "Método responsável por excluir definitivamente o cliente.")
    public ResponseEntity DeleteClient(@PathVariable UUID id) {
        return clientServices.deleteById(id);
    }

}
