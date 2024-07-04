package br.com.ezblue.ezclientservices.domain.vehicle;

import jakarta.validation.constraints.NotNull;

public record RegisterVehicle(
        @NotNull
        String manufacturer,
        @NotNull
        String model,
        @NotNull
        int year,
        @NotNull
        String licensePlate
) {
}
