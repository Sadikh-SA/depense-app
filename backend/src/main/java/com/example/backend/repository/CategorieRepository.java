package com.example.backend.repository;

import com.example.backend.model.Categorie;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findByUser(User user);
    List<Categorie> findByNameContainingIgnoreCase(String keyword);
}

