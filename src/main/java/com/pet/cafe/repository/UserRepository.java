package com.pet.cafe.repository;

import com.pet.cafe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailVerificationToken(String token);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
//    Optional<User> findById(String id);
}
