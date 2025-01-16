package io.github.materialcontrol.ms_users.services;

import io.github.materialcontrol.ms_users.entities.enums.Role;
import io.github.materialcontrol.ms_users.entities.user.User;
import io.github.materialcontrol.ms_users.entities.user.dtos.UserUpdateDto;
import io.github.materialcontrol.ms_users.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.github.materialcontrol.ms_users.common.UserConstants.ENCODED_PASSWORD;
import static io.github.materialcontrol.ms_users.common.UserConstants.VALID_USER;
import static io.github.materialcontrol.ms_users.common.UserConstants.VALID_USER_SAVED;
import static io.github.materialcontrol.ms_users.common.UserConstants.USER_UPDATE_DTO;
import static io.github.materialcontrol.ms_users.common.UserConstants.VALID_USER_UPDATE;
import static io.github.materialcontrol.ms_users.common.UserConstants.USER_UPDATE_PASSWORD_DTO;
import static io.github.materialcontrol.ms_users.common.UserConstants.ENCODED_PASSWORD_UPDATE;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @Test
    public void createUser_WithValidData_ReturnsUser() {
        when(encoder.encode(VALID_USER.getPassword())).thenReturn(ENCODED_PASSWORD);
        when(repository.save(VALID_USER)).thenReturn(VALID_USER_SAVED);

        User sut = service.create(VALID_USER);

        assertThat(sut.getId()).isNotNull();
        assertThat(sut).isEqualTo(VALID_USER_SAVED);
    }

    @Test
    public void getUser_ByExistingId_ReturnsUser() {
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_USER_SAVED));

        User sut = service.findById(1L);

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(VALID_USER_SAVED);
    }

    @Test
    public void getAllUsers_Void_ReturnsPageUsers() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "id"));
        List<User> userList = List.of(
                new User(1L, "José", "123456", ENCODED_PASSWORD, Role.ROLE_PUBLIC),
                new User(2L, "Maria", "123456", ENCODED_PASSWORD, Role.ROLE_PUBLIC),
                new User(3L, "José Maria", "123456", ENCODED_PASSWORD, Role.ROLE_PUBLIC)
        );
        Page<User> userPage = new PageImpl<>(userList);

        when(repository.findAll(pageable)).thenReturn(userPage);

        Page<User> sut = service.findAll(pageable);

        assertThat(sut).isNotNull();
        assertThat(sut.getSize()).isEqualTo(3);
    }

    @Test
    public void getAllUsers_ByName_ReturnsPageUsers() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "id"));
        List<User> userList = List.of(
                new User(1L, "José", "josezin", ENCODED_PASSWORD, Role.ROLE_PUBLIC),
                new User(2L, "Maria", "mariazinha", ENCODED_PASSWORD, Role.ROLE_PUBLIC),
                new User(3L, "José Maria", "josemaria", ENCODED_PASSWORD, Role.ROLE_PUBLIC)
        );
        Page<User> userPage = new PageImpl<>(userList
                .stream()
                .filter(user -> user.getName().toLowerCase().contains("maria")).toList()
        );

        when(repository.findByNameLikeIgnoreCase(pageable, "%maria%")).thenReturn(userPage);

        Page<User> sut = service.findByName(pageable, "maria");

        assertThat(sut).isNotNull();
        assertThat(sut.getSize()).isEqualTo(2);
        assertThat(sut.stream().toList().get(0).getUsername()).isEqualTo("mariazinha");
    }

    @Test
    public void updateUser_WithValidData_ReturnsVoid() {
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_USER_SAVED));
        when(repository.existsUserByUsername(VALID_USER_UPDATE.getUsername())).thenReturn(false);

        service.update(1L, USER_UPDATE_DTO);

        assertThat(VALID_USER_SAVED.getUsername()).isEqualTo(USER_UPDATE_DTO.getUsername());
        assertThat(VALID_USER_SAVED.getName()).isEqualTo(USER_UPDATE_DTO.getName());
    }

    @Test
    public void updateUserPassword_WithValidData_ReturnsVoid() {
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_USER_SAVED));
        when(encoder.matches(USER_UPDATE_PASSWORD_DTO.getPassword(), VALID_USER_SAVED.getPassword())).thenReturn(true);
        when(encoder.encode(USER_UPDATE_PASSWORD_DTO.getNewPassword())).thenReturn(ENCODED_PASSWORD_UPDATE);

        service.updatePassword(1L, USER_UPDATE_PASSWORD_DTO);

        assertThat(VALID_USER_SAVED.getPassword()).isEqualTo(ENCODED_PASSWORD_UPDATE);
    }
}
