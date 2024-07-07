package br.com.ezblue.ezclientservices.domain.vehicle;

/**
 * Formulário de atualização do veículo.
 *
 * @param manufacturer Fabricante do veículo.
 * @param model        Modelo do veículo.
 * @param year         Ano do veículo.
 * @param licensePlate Placa do veículo.
 */
public record UpdateVehicle(
        String manufacturer,
        String model,
        Integer year,
        String licensePlate
) {
}
