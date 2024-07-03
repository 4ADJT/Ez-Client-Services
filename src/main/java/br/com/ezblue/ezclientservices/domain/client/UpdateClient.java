package br.com.ezblue.ezclientservices.domain.client;

import br.com.ezblue.ezclientservices.domain.address.RegisterAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdateClient(
        @Email
        String email,
        @Pattern(regexp = "^\\d{11}$")
        String phone,
        @Valid
        RegisterAddress address
){
}
