package br.com.ezblue.ezclientservices.domain.client;

import br.com.ezblue.ezclientservices.domain.address.RegisterAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

/**
 * Formulário de atualização dos dados do cliente.
 *
 * @param email   E-mail do cliente.
 * @param phone   Telefone do cliente.
 * @param address Objeto {@code RegisterAddress} para o registro de endereço do cliente.
 */
public record UpdateClient(
        @Email
        String email,
        @Pattern(regexp = "^\\d{11}$")
        String phone,
        @Valid
        RegisterAddress address
) {
}
