package io.github.materialcontrol.ms_users.entities.user.dtos;

import io.github.materialcontrol.ms_users.controllers.UserController;
import io.github.materialcontrol.ms_users.entities.user.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;

public class UserMapper {
    public static User toEntity(UserRequestDto dto) {
        return new ModelMapper().map(dto, User.class);
    }

    public static UserResponseDto toDto(User user) {
        String role = user.getRole().name().replace("ROLE_", "");

        PropertyMap<User, UserResponseDto> props = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(props);

        return modelMapper.map(user, UserResponseDto.class);
    }

    public static Page<UserResponseDto> toPage(Page<User> users) {
        return users.map(UserMapper::toDto);
    }
}
