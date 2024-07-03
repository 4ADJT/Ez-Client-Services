package br.com.ezblue.ezclientservices.domain.client;


import br.com.ezblue.ezclientservices.domain.address.DetailAddress;

import java.util.UUID;

public record DetailClient(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        DetailAddress address
) {
    public DetailClient(ClientEntity client) {
        this(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone(),
                new DetailAddress(client.getAddress())
        );
    }
}
