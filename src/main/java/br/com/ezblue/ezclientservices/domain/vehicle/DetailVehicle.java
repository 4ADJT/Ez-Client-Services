package br.com.ezblue.ezclientservices.domain.vehicle;

import java.util.UUID;

public record DetailVehicle(
        UUID id,
        String manufacturer,
        String model,
        int year,
        String licensePlate
) {
    public DetailVehicle(VehicleEntity vehicleEntity) {
        this(
                vehicleEntity.getId(),
                vehicleEntity.getManufacturer(),
                vehicleEntity.getModel(),
                vehicleEntity.getYear(),
                vehicleEntity.getLicensePlate()
        );
    }
}
