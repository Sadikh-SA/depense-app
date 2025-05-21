package com.example.backend.repository;

import com.example.backend.model.Depense;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DepenseRepository extends JpaRepository<Depense, Long> {
    List<Depense> findByUser(User user);
    List<Depense> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
    List<Depense> findByDescriptionContainingIgnoreCase(String keyword);
}
