package io.github.materialcontrol.ms_users.entities.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$")
    private String email;

    @NotNull
    @Size(min = 5, max = 60)
    private String password;
}