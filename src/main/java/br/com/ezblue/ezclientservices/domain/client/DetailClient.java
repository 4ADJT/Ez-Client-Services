package br.com.ezblue.ezclientservices.domain.client;


import br.com.ezblue.ezclientservices.domain.address.DetailAddress;
import br.com.ezblue.ezclientservices.domain.vehicle.SimpleVehicle;

import java.util.List;
import java.util.UUID;

public record DetailClient(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        DetailAddress address,
        List<SimpleVehicle> vehicle
) {
    public DetailClient(ClientEntity client) {
        this(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone(),
                new DetailAddress(client.getAddress()),
                client.getVehicles() != null ? client.getVehicles().stream()
                        .map(SimpleVehicle::new)
                        .toList() : null
        );
    }
}
