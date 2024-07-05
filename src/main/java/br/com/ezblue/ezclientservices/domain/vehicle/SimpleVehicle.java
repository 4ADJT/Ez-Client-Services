package br.com.ezblue.ezclientservices.domain.vehicle;

import java.util.UUID;

public record SimpleVehicle(
        UUID id,
        String manufacturer,
        String model,
        Integer year,
        String licensePlate
) {
    public SimpleVehicle(VehicleEntity vehicleEntity) {
        this(
                vehicleEntity.getId(),
                vehicleEntity.getManufacturer(),
                vehicleEntity.getModel(),
                vehicleEntity.getYear(),
                vehicleEntity.getLicensePlate()
        );
    }
}
