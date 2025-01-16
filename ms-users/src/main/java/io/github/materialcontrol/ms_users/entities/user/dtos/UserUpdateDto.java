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
public class UserUpdateDto {
    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 3, max = 50)
    private String username;
}