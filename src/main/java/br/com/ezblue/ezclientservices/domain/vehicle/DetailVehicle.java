package br.com.ezblue.ezclientservices.domain.vehicle;

import br.com.ezblue.ezclientservices.domain.client.DetailClient;
import br.com.ezblue.ezclientservices.domain.client.SimpleClient;

import java.util.List;
import java.util.UUID;

public record DetailVehicle(
        UUID id,
        String manufacturer,
        String model,
        int year,
        String licensePlate,
        List<SimpleClient> clients
) {
    public DetailVehicle(VehicleEntity vehicleEntity) {
        this(
                vehicleEntity.getId(),
                vehicleEntity.getManufacturer(),
                vehicleEntity.getModel(),
                vehicleEntity.getYear(),
                vehicleEntity.getLicensePlate(),
                vehicleEntity.getClients().stream()
                        .map(SimpleClient::new).toList()
        );
    }

    public DetailVehicle(VehicleEntity vehicleEntity, List<SimpleClient> simpleClients) {
        this(
                vehicleEntity.getId(),
                vehicleEntity.getManufacturer(),
                vehicleEntity.getModel(),
                vehicleEntity.getYear(),
                vehicleEntity.getLicensePlate(),
                simpleClients
        );
    }
}
