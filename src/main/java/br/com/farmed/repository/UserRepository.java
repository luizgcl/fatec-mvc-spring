package br.com.farmed.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmed.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameUser(String nameUser);
    
}
