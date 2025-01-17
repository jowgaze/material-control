package io.github.materialcontrol.ms_users.entities.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto extends RepresentationModel<UserResponseDto> {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String role;
}
