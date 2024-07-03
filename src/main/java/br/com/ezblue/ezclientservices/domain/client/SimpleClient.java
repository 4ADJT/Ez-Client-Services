package br.com.ezblue.ezclientservices.domain.client;

import java.util.UUID;

public record SimpleClient(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
    public SimpleClient(ClientEntity client) {
        this(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone()
        );
    }
}
