package io.github.materialcontrol.ms_users.controllers;

import io.github.materialcontrol.ms_users.entities.user.User;
import io.github.materialcontrol.ms_users.entities.user.dtos.*;
import io.github.materialcontrol.ms_users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserRequestDto body) {
        User user = userService.create(UserMapper.toEntity(body));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        Direction sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));

        Page<UserResponseDto> users = UserMapper.toPage(userService.findAll(pageable));
        return ResponseEntity.ok().body(users.map(item -> item
                .add(linkTo(methodOn(UserController.class).findById(item.getId())).withSelfRel()))
        );
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Page<UserResponseDto>> findByName(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @PathVariable("name") String name) {

        Direction sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));

        Page<UserResponseDto> users = UserMapper.toPage(userService.findByName(pageable, name));
        return ResponseEntity.ok().body(users.map(item -> item
                .add(linkTo(methodOn(UserController.class).findById(item.getId())).withSelfRel()))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id) {
        UserResponseDto response = UserMapper.toDto(userService.findById(id));
        response.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDto body) {
        userService.update(id, body);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") Long id, @RequestBody @Valid UserUpdatePasswordDto body) {
        userService.updatePassword(id, body);
        return ResponseEntity.noContent().build();
    }
}
