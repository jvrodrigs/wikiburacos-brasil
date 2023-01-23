package com.wiki.backend.Repository;

import com.wiki.backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailEquals(String email);
    Optional<User> findByUsernameEquals(String username);
}
