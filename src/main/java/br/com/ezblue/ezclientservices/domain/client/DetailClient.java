package br.com.ezblue.ezclientservices.domain.client;


import br.com.ezblue.ezclientservices.domain.address.DetailAddress;
import br.com.ezblue.ezclientservices.domain.vehicle.SimpleVehicle;

import java.util.List;
import java.util.UUID;

/**
 * Objeto Record usado para devolver os dados detalhados do cliente.
 *
 * @param id        Identificador único do cadastro do cliente.
 * @param firstName Primeiro nome do cliente.
 * @param lastName  Ultimo nome do cliente.
 * @param email     E-mail do cliente.
 * @param phone     telefone do cliente.
 * @param address   Objeto {@code DetailAddress} para exibir as informações completas do endereço do cliente.
 * @param vehicle   Objeto {@code SimpleVehicle} para exibir as informações baica do cliente.
 */
public record DetailClient(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        DetailAddress address,
        List<SimpleVehicle> vehicle
) {

    /**
     * Construtor que inicializa um objeto {@code DetailClient} a partir de uma entidade {@code ClientEntity}.
     *
     * @param clientEntity O objeto da entidade do cliente.
     */
    public DetailClient(ClientEntity clientEntity) {
        this(
                clientEntity.getId(),
                clientEntity.getFirstName(),
                clientEntity.getLastName(),
                clientEntity.getEmail(),
                clientEntity.getPhone(),
                new DetailAddress(clientEntity.getAddress()),
                clientEntity.getVehicles() != null ? clientEntity.getVehicles().stream()
                        .map(SimpleVehicle::new)
                        .toList() : null
        );
    }
}
