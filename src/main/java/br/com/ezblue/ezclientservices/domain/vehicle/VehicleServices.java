package br.com.ezblue.ezclientservices.domain.vehicle;

import br.com.ezblue.ezclientservices.domain.client.ClientServices;
import br.com.ezblue.ezclientservices.domain.client.SimpleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleServices {

    @Autowired
    private ClientServices clientServices;

    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * Registrar um veículo para um cliente.
     *
     * @param clientId O ID do cliente que o carro sera a vinculado.
     * @return DetailVehicle objeto contem todas as informações do veículo e as informações básicas do cliente.
     * @throws RuntimeException caso o cliente não seja encontrado.
     */
    public DetailVehicle register(UUID clientId, RegisterVehicle registerVehicle) {
        return clientServices.optionalFindById(clientId).map(clientEntity -> {
            VehicleEntity vehicleEntity = new VehicleEntity(registerVehicle);
            vehicleRepository.save(vehicleEntity);
            clientEntity.getVehicles().add(vehicleEntity);
            return new DetailVehicle(vehicleEntity);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    /**
     * Detalhar um veículo.
     *
     * @param id O ID do veículo.
     * @return DetailVehicle objeto contem todas as informações do veículo e as informações básicas de todos os clientes vinculado a ele.
     * @throws RuntimeException caso o veículo não seja encontrado.
     */
    public DetailVehicle findVehicleById(UUID id) {
        return vehicleRepository.findById(id).map(vehicleEntity -> {
            var simpleClients = vehicleEntity.getClients().stream().map(SimpleClient::new).toList();
            return new DetailVehicle(vehicleEntity, simpleClients);
        }).orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    /**
     * Detalhar um veículo do cliente.
     *
     * @param clientId o ID do cliente.
     * @param id O ID do veículo.
     * @return DetailVehicle objeto contem todas as informações do veículo e as informações básicas do cliente.
     * @throws RuntimeException caso o veículo não seja encontrado.
     */
    public DetailVehicle findClientVehicleById(UUID clientId, UUID id) {
        return vehicleRepository.findById(id).map(vehicleEntity -> {
            var simpleClients = vehicleEntity.getClients().stream()
                    .filter(clientEntity -> clientEntity.getId().equals(clientId))
                    .map(SimpleClient::new).toList();
            return new DetailVehicle(vehicleEntity, simpleClients);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
