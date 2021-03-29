package com.business.application.repository;

import com.business.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);
}
