package br.com.ezblue.ezclientservices.domain.client;

import br.com.ezblue.ezclientservices.domain.address.RegisterAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Formulário de cadastro de cliente.
 *
 * @param firstName Primeiro nome do cliente.
 * @param lastName  Ultimo nome do cliente.
 * @param email     E-mail do cliente.
 * @param phone     telefone do cliente.
 * @param address   Objeto {@code RegisterAddress} usado para cadastrar o endereço do cliente.
 */
public record RegisterClient(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @Email
        String email,
        @NotNull
        @Pattern(regexp = "^\\d{11}$")
        String phone,
        @Valid
        @NotNull
        RegisterAddress address
) {
}
