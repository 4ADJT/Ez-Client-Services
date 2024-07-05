package br.com.ezblue.ezclientservices.domain.vehicle;

import jakarta.validation.constraints.NotNull;

public record UpdateVehicle(
        String manufacturer,
        String model,
        Integer year,
        String licensePlate
) {
}
