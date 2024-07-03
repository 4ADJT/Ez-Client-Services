package br.com.ezblue.ezclientservices.domain.address;

import java.util.UUID;

public record DetailAddress(
        UUID id,
        String street,
        int number,
        String country,
        String city,
        String state,
        String zip
) {
    public DetailAddress(AddressEntity address) {
        this(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getCountry(),
                address.getCity(),
                address.getState(),
                address.getZip()
        );
    }
}
