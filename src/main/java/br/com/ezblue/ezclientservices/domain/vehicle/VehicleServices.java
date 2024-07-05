package br.com.ezblue.ezclientservices.domain.vehicle;

import br.com.ezblue.ezclientservices.domain.client.ClientServices;
import br.com.ezblue.ezclientservices.domain.client.SimpleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return clientServices.findById(clientId).map(clientEntity -> {
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
     * @param id       O ID do veículo.
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

    /**
     * Listar veículos.
     *
     * @param pageable Objeto de paginação.
     * @return Page<SimpleVehicle> objeto retorna uma lista de todos os veículos cadastrados com as informações básicas.
     */
    public Page<SimpleVehicle> findAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable).map(SimpleVehicle::new);
    }

    /**
     * Atualizar um veículo.
     *
     * @param id            o ID do veículo a ser atualizado.
     * @param updateVehicle formulário de atualização do veículo.
     * @return DetailVehicle objeto retorna todos os dados do veículo.
     */
    public DetailVehicle updateVehicle(UUID clientId, UUID id, UpdateVehicle updateVehicle) {
        var clientEntity = clientServices.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        var vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (!vehicleEntity.getClients().contains(clientEntity))
            throw new RuntimeException("Vehicle does not belong to the clientEntity");

        vehicleEntity.updateData(updateVehicle);
        return new DetailVehicle(vehicleEntity);
    }

    /**
     * Exclusão física, exclui o veículo permanentemente.
     *
     * @param clientId O ID do cliente.
     * @param id       O ID do veículo a ser excluído.
     */
    public void deleteById(UUID clientId, UUID id) {
        var vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        var clientEntity = clientServices.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        clientEntity.getVehicles().remove(vehicleEntity);
        vehicleEntity.getClients().remove(clientEntity);

        clientServices.save(clientEntity);
        vehicleRepository.save(vehicleEntity);
    }

}
