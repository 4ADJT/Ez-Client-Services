package br.com.ezblue.ezclientservices.domain.vehicle;

import br.com.ezblue.ezclientservices.domain.client.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
public class VehicleServices {

    @Autowired
    private ClientServices clientServices;

    @Autowired
    private VehicleRepository vehicleRepository;

    public DetailVehicle register(UUID clientId, RegisterVehicle registerVehicle) {
        return clientServices.findById(clientId).map(clientEntity -> {
            VehicleEntity vehicleEntity = new VehicleEntity(registerVehicle);
            vehicleRepository.save(vehicleEntity);
            clientEntity.getVehicles().add(vehicleEntity);
            return new DetailVehicle(vehicleEntity);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
