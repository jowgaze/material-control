package io.github.materialcontrol.ms_users.entities.user.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdatePasswordDto {
    @Size(min = 5, max = 60)
    private String password;

    @Size(min = 5, max = 60)
    private String newPassword;

    @Size(min = 5, max = 60)
    private String confirmNewPassword;
}