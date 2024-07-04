package br.com.ezblue.ezclientservices.controller;

import br.com.ezblue.ezclientservices.domain.client.ClientServices;
import br.com.ezblue.ezclientservices.domain.client.DetailClient;
import br.com.ezblue.ezclientservices.domain.client.RegisterClient;
import br.com.ezblue.ezclientservices.domain.vehicle.DetailVehicle;
import br.com.ezblue.ezclientservices.domain.vehicle.RegisterVehicle;
import br.com.ezblue.ezclientservices.domain.vehicle.VehicleServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/client/{clientId}/vehicle")
public class ClientVehicleController {

    @Autowired
    private VehicleServices vehicleServices;

    @PostMapping
    @Transactional
    @Operation(summary = "Registar Veículo", description = "Método responsável por registrar novos veículos do clientes")
    public ResponseEntity<DetailVehicle> registerVehicle(@PathVariable UUID clientId, @RequestBody @Valid RegisterVehicle registerVehicle, UriComponentsBuilder componentsBuilder) {
        var vehicle =  vehicleServices.register(clientId,registerVehicle);
        var uri = componentsBuilder.path("client/{clientId}/vehicle/{id}").buildAndExpand(clientId,vehicle.id()).toUri();
        return ResponseEntity.created(uri).body(vehicle);
    }
}
