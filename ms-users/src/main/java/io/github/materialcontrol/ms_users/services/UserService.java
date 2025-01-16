package io.github.materialcontrol.ms_users.services;

import io.github.materialcontrol.ms_users.entities.user.User;
import io.github.materialcontrol.ms_users.entities.user.dtos.UserUpdateDto;
import io.github.materialcontrol.ms_users.entities.user.dtos.UserUpdatePasswordDto;
import io.github.materialcontrol.ms_users.exceptions.InvalidPasswordException;
import io.github.materialcontrol.ms_users.exceptions.NotFoundException;
import io.github.materialcontrol.ms_users.exceptions.UniqueAttributeViolationException;
import io.github.materialcontrol.ms_users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public User create(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueAttributeViolationException(
                    String.format("Username = %s already exists", user.getUsername())
            );
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with id = %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<User> findByName(Pageable pageable, String name) {
        name = "%" + name + "%";
        return userRepository.findByNameLikeIgnoreCase(pageable, name);
    }

    @Transactional
    public void update(Long id, UserUpdateDto update) {
        User user = findById(id);

        if (update.getUsername() != null) {
            if (!update.getUsername().equals(user.getUsername())) {
                if (userRepository.existsUserByUsername(update.getUsername())) {
                    throw new UniqueAttributeViolationException(
                            String.format("Username = %s already exists", update.getUsername())
                    );
                }
                user.setUsername(update.getUsername());
            }
        }

        if (update.getName() != null)
            user.setName(update.getName());
    }

    @Transactional
    public void updatePassword(Long id, UserUpdatePasswordDto passwords) {
        if (!passwords.getNewPassword().equals(passwords.getConfirmNewPassword()))
            throw new InvalidPasswordException("Incompatible passwords");

        User user = findById(id);

        if (!encoder.matches(passwords.getPassword(), user.getPassword()))
            throw new InvalidPasswordException("Incorrect password");

        user.setPassword(encoder.encode(passwords.getNewPassword()));
    }

}
