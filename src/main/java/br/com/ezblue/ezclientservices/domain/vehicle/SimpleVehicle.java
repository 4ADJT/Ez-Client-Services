package br.com.ezblue.ezclientservices.domain.vehicle;

import java.util.UUID;

/**
 * Objeto Record usado para devolver os dados simplificados do veículo.
 *
 * @param id           Identificador único do veículo.
 * @param manufacturer Fabricante do veículo.
 * @param model        Modelo do veículo.
 * @param year         Ano do veículo.
 * @param licensePlate Placa do veículo.
 */
public record SimpleVehicle(
        UUID id,
        String manufacturer,
        String model,
        Integer year,
        String licensePlate
) {

    /**
     * Construtor que inicializa um objeto {@code SimpleVehicle} a partir de uma entidade {@code vehicleEntity}.
     *
     * @param vehicleEntity Objeto de entidade do veículo.
     */
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
