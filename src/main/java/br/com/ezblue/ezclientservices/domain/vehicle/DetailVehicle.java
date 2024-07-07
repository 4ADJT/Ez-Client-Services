package br.com.ezblue.ezclientservices.domain.vehicle;

import br.com.ezblue.ezclientservices.domain.client.SimpleClient;

import java.util.List;
import java.util.UUID;

/**
 * Objeto Record usado para devolver os dados detalhados do veículo.
 *
 * @param id           Identificador único do veículo.
 * @param manufacturer Fabricante do veículo.
 * @param model        Modelo do veículo.
 * @param year         Ano do veículo.
 * @param licensePlate Placa do veículo.
 * @param clients      Objeto {@code SimpleClient} para exibir informações básicas do cliente.
 */
public record DetailVehicle(
        UUID id,
        String manufacturer,
        String model,
        int year,
        String licensePlate,
        List<SimpleClient> clients
) {

    /**
     * Construtor que inicializa um objeto {@code DetailVehicle} a partir de uma entidade {@code vehicleEntity}.
     *
     * @param vehicleEntity Objeto de entidade do veículo.
     */
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

    /**
     * Construtor que inicializa um objeto {@code DetailVehicle} a partir de uma entidade {@code vehicleEntity} e uma lista de objeto {@code SimpleClient}.
     *
     * @param vehicleEntity Objeto de entidade do veículo.
     */
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
