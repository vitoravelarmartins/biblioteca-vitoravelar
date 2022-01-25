package com.vitor.biblioteca.models.repository;

import com.vitor.biblioteca.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    public List<UserModel> findByNameIgnoreCase(String name);
    public Optional<UserModel> findByNameContains(String name);
}
