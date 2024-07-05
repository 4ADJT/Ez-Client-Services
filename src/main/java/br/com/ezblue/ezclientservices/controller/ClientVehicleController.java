package br.com.ezblue.ezclientservices.controller;

import br.com.ezblue.ezclientservices.domain.vehicle.DetailVehicle;
import br.com.ezblue.ezclientservices.domain.vehicle.RegisterVehicle;
import br.com.ezblue.ezclientservices.domain.vehicle.VehicleServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(summary = "Registar Veículo", description = "Método responsável por registrar novos veículos ao cliente.")
    public ResponseEntity<DetailVehicle> registerVehicle(@PathVariable UUID clientId, @RequestBody @Valid RegisterVehicle registerVehicle, UriComponentsBuilder componentsBuilder) {
        var vehicle = vehicleServices.register(clientId, registerVehicle);
        var uri = componentsBuilder.path("client/{clientId}/vehicle/{id}").buildAndExpand(clientId, vehicle.id()).toUri();
        return ResponseEntity.created(uri).body(vehicle);
    }

    @GetMapping("/{clientId}/vehicle/{id}")
    @Operation(summary = "Detalhar veiculo do cliente", description = "Método responsável por detalhar dados do veículo do cliente.")
    public ResponseEntity<DetailVehicle> getClientVehicle(@PathVariable UUID clientId, @PathVariable UUID id) {
        return ResponseEntity.ok(vehicleServices.findClientVehicleById(clientId, id));
    }


    @GetMapping("/vehicle/{id}")
    @Operation(summary = "Detalhar veiculo", description = "Método responsável por detalhar dados do veículo exibir todos os cliente vinculados ele.")
    public ResponseEntity<DetailVehicle> getVehicle(@PathVariable UUID id) {
        return ResponseEntity.ok(vehicleServices.findVehicleById(id));
    }
}
