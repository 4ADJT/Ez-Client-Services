package br.com.ezblue.ezclientservices.controller;

import br.com.ezblue.ezclientservices.domain.client.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/client")
@Tag(name = "Ez Client Service", description = "Serviço para gerenciamento de Clientes.")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar Novo Cliente", description = "Registra um novo cliente no sistema.")
    public ResponseEntity<DetailClient> registerClient(@RequestBody @Valid RegisterClient registerClient, UriComponentsBuilder componentsBuilder) {
        var client = clientServices.register(registerClient);
        var uri = componentsBuilder.path("client/{id}").buildAndExpand(client.id()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @GetMapping
    @Operation(summary = "Listar Clientes", description = "Lista todos os clientes cadastrados.")
    public ResponseEntity<Page<SimpleClient>> ListClients(@PageableDefault(size = 10, sort = {"firstName", "lastName"}) Pageable pageable) {
        return ResponseEntity.ok(clientServices.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar Cliente", description = "Exibe os detalhes de um cliente específico.")
    public ResponseEntity<DetailClient> detailClient(@PathVariable UUID id) {
        return ResponseEntity.ok(clientServices.ConvertToDetailClient(clientServices.findById(id)));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar Cliente", description = "Atualiza os dados de um cliente específico.")
    public ResponseEntity<DetailClient> UpdateClient(@RequestBody @Valid UpdateClient updateClient, @PathVariable UUID id) {
        return ResponseEntity.ok(clientServices.getReferenceById(updateClient, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Cliente", description = "Exclui permanentemente um cliente específico.")
    public ResponseEntity DeleteClient(@PathVariable UUID id) {
        clientServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //TODO: fazer exclusão logica do cliente

}
