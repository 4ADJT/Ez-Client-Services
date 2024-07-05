package br.com.ezblue.ezclientservices.controller;

import br.com.ezblue.ezclientservices.domain.vehicle.*;
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
@Tag(name = "Ez Vehicle Service", description = "Serviço para gerenciamento de veículos.")
public class ClientVehicleController {

    @Autowired
    private VehicleServices vehicleServices;

    @PostMapping("/{clientId}/vehicle")
    @Transactional
    @Operation(summary = "Registrar Veículo", description = "Registra um novo veículo para um cliente específico.")
    public ResponseEntity<DetailVehicle> registerVehicle(@PathVariable UUID clientId, @RequestBody @Valid RegisterVehicle registerVehicle, UriComponentsBuilder componentsBuilder) {
        var vehicle = vehicleServices.register(clientId, registerVehicle);
        var uri = componentsBuilder.path("client/{clientId}/vehicle/{id}").buildAndExpand(clientId, vehicle.id()).toUri();
        return ResponseEntity.created(uri).body(vehicle);
    }

    @GetMapping("/{clientId}/vehicle/{id}")
    @Operation(summary = "Detalhar Veículo do Cliente", description = "Exibe os detalhes de um veículo específico associado a um cliente.")
    public ResponseEntity<DetailVehicle> getClientVehicle(@PathVariable UUID clientId, @PathVariable UUID id) {
        return ResponseEntity.ok(vehicleServices.findClientVehicleById(clientId, id));
    }

    @GetMapping("/vehicle")
    @Operation(summary = "Listar Veículos", description = "Lista todos os veículos cadastrados.")
    public ResponseEntity<Page<SimpleVehicle>> listVehicles(@PageableDefault(size = 10, sort = {"manufacturer", "model", "year"}) Pageable pageable) {
        return ResponseEntity.ok(vehicleServices.findAll(pageable));
    }

    @GetMapping("/vehicle/{id}")
    @Operation(summary = "Detalhar Veículo", description = "Exibe os detalhes de um veículo específico, incluindo todos os clientes associados a ele.")
    public ResponseEntity<DetailVehicle> getVehicle(@PathVariable UUID id) {
        return ResponseEntity.ok(vehicleServices.findVehicleById(id));
    }

    @PutMapping("/{clientId}/vehicle/{id}")
    @Transactional
    @Operation(summary = "Atualizar Veículo do Cliente", description = "Atualiza os dados de um veículo específico de um cliente.")
    public ResponseEntity<DetailVehicle> updateVehicle(@PathVariable UUID clientId, @PathVariable UUID id, @RequestBody @Valid UpdateVehicle updateVehicle) {
        return ResponseEntity.ok(vehicleServices.updateVehicle(clientId, id, updateVehicle));
    }

    @DeleteMapping("/{clientId}/vehicle/{id}")
    @Operation(summary = "Excluir Veículo do Cliente", description = "Exclui permanentemente um veículo associado a um cliente específico.")
    public ResponseEntity deleteVehicle(@PathVariable UUID clientId, @PathVariable UUID id) {
        vehicleServices.deleteById(clientId, id);
        return ResponseEntity.noContent().build();
    }

    //TODO: fazer exclusão logica do cliente
}
