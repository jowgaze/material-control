package io.github.materialcontrol.ms_users.repositories;

import io.github.materialcontrol.ms_users.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);
    Page<User> findByNameLikeIgnoreCase(Pageable pageable, String name);
}