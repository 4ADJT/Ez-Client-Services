package br.com.ezblue.ezclientservices.domain.address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Formulário de cadastro do endereço.
 *
 * @param street  O nome da rua da residência.
 * @param number  O número da residência.
 * @param country O nome do país da residência.
 * @param city    O nome da cidade da residência.
 * @param state   O estado da cidade da residência.
 * @param zip     O código postal da residência.
 */
public record RegisterAddress(
        String street,
        @NotNull
        int number,
        String country,
        String city,
        String state,
        @NotNull
        @Pattern(regexp = "^\\d{5}-\\d{3}$")
        String zip
) {
}
