package com.ForoHub.ForoHubChallenge.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DataNewUser(
        @NotBlank(message = "El nombre es obligatorio.")
        String username,
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser valido")
        String email,
        @NotBlank(message = "La contraseña es obligatoria.")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
        String password

) {
}
