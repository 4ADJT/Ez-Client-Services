package br.com.ezblue.ezclientservices.domain.vehicle;

import jakarta.validation.constraints.NotNull;

/**
 * Formulário de cadastro do veículo.
 *
 * @param manufacturer Fabricante do veículo.
 * @param model        Modelo do veículo.
 * @param year         Ano do veículo.
 * @param licensePlate Placa do veículo.
 */
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
