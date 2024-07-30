package br.com.ezblue.ezclientservices.domain.address;

import java.util.UUID;

/**
 * Objeto Record usado para devolver os dados detalhados do endereço.
 *
 * @param id      O Identificador único do endereço.
 * @param street  O nome da rua da residência.
 * @param number  O número da residência.
 * @param country O nome do país da residência.
 * @param city    O nome da cidade da residência.
 * @param state   O estado da cidade da residência.
 * @param zip     O código postal da residência.
 */
public record DetailAddress(
        UUID id,
        String street,
        int number,
        String country,
        String city,
        String state,
        String zip
) {

    /**
     * Construtor que inicializa um objeto {@code DetailAddress} a partir de uma entidade {@code AddressEntity}.
     *
     * @param addressEntity O objeto da entidade do endereço.
     */
    public DetailAddress(AddressEntity addressEntity) {
        this(
                addressEntity.getId(),
                addressEntity.getStreet(),
                addressEntity.getNumber(),
                addressEntity.getCountry(),
                addressEntity.getCity(),
                addressEntity.getState(),
                addressEntity.getZip()
        );
    }
}
