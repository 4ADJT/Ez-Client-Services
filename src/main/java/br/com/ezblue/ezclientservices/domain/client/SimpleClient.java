package br.com.ezblue.ezclientservices.domain.client;

import java.util.UUID;

/**
 * Objeto Record usado para devolver os dados simples do cliente.
 *
 * @param id        Identificador único do cadastro do cliente.
 * @param firstName Primeiro nome do cliente.
 * @param lastName  Ultimo nome do cliente.
 * @param email     E-mail do cliente.
 * @param phone     telefone do cliente.
 */
public record SimpleClient(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone
) {

    /**
     * Construtor que inicializa um objeto {@code SimpleClient} a partir de uma entidade {@code ClientEntity}.
     *
     * @param clientEntity O objeto da entidade do cliente.
     */
    public SimpleClient(ClientEntity clientEntity) {
        this(
                clientEntity.getId(),
                clientEntity.getFirstName(),
                clientEntity.getLastName(),
                clientEntity.getEmail(),
                clientEntity.getPhone()
        );
    }
}
