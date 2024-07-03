package br.com.ezblue.ezclientservices.domain.client;

import br.com.ezblue.ezclientservices.domain.address.RegisterAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
