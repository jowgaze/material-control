package io.github.materialcontrol.ms_loan.services.client.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String role;
}
